package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectAuthorizeUserRoleContext;
import com.wondernect.elements.authorize.context.config.WondernectServerContextConfigProperties;
import com.wondernect.elements.authorize.context.config.WondernectUserRoleContextConfigProperties;
import com.wondernect.elements.authorize.context.impl.DefaultWondernectCommonContext;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectAuthorizeContextInterceptor
 * Author: chenxun
 * Date: 2019/3/27 13:34
 * Description:
 */
public class WondernectUserRoleHandlerInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(WondernectUserRoleHandlerInterceptor.class);

    @Autowired
    private WondernectServerContextConfigProperties wondernectServerContextConfigProperties;

    @Autowired
    private WondernectUserRoleContextConfigProperties wondernectAuthorizeContextConfigProperties;

    @Autowired
    private WondernectAuthorizeUserRoleContext wondernectAuthorizeUserRoleContext;

    @Autowired
    private DefaultWondernectCommonContext wondernectCommonContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!wondernectAuthorizeContextConfigProperties.isEnable()) {
            return true;
        }
        if (!(handler instanceof HandlerMethod)) {
            throw new BusinessException(BusinessError.INVALID_REQUEST_URL);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AuthorizeUserRole authorizeUserRole = method.getAnnotation(AuthorizeUserRole.class);
        if (null == authorizeUserRole) {
            return true;
        }
        // 设置requestId
        request.setAttribute(wondernectServerContextConfigProperties.getRequestPropertyName(), wondernectCommonContext.getRequestId());
        String expiresToken;
        String unlimitedToken;
        AuthorizeType authorizeType = authorizeUserRole.authorizeType();
        switch (authorizeType) {
            case EXPIRES_TOKEN:
            {
                expiresToken = request.getHeader(wondernectAuthorizeContextConfigProperties.getExpiresTokenPropertyName());
                if (ESStringUtils.isBlank(expiresToken)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_HEADER_IS_NULL);
                }
                authorizeExpiresToken(expiresToken);
                break;
            }
            case UNLIMITED_TOKEN:
            {
                unlimitedToken = request.getHeader(wondernectAuthorizeContextConfigProperties.getUnlimitedTokenPropertyName());
                if (ESStringUtils.isBlank(unlimitedToken)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_HEADER_IS_NULL);
                }
                authorizeUnlimitedToken(unlimitedToken);
                break;
            }
            default:
            {
                throw new BusinessException(BusinessError.AUTHORIZE_TYPE_IS_INVALID);
            }
        }
        // 设置userId
        request.setAttribute(wondernectServerContextConfigProperties.getUserPropertyName(), wondernectCommonContext.getAuthorizeData().getUserId());
        // 设置appId
        request.setAttribute(wondernectServerContextConfigProperties.getAppPropertyName(), wondernectCommonContext.getAuthorizeData().getAppId());
        // 权限验证
        AuthorizeRoleType authorizeRoleType = authorizeUserRole.authorizeRoleType();
        switch (authorizeRoleType) {
            case ONLY_AUTHORIZE:
            {
                break;
            }
            case CONFIG:
            {
                if (ArrayUtils.isEmpty(authorizeUserRole.validUserRoles())) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                if (wondernectAuthorizeUserRoleContext.authorizeUserRole(wondernectCommonContext.getAuthorizeData().getRole(), Arrays.asList(authorizeUserRole.validUserRoles()))) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                break;
            }
            case CUSTOM:
            {
                List<String> validUserRoleList = wondernectAuthorizeUserRoleContext.getCustomValidUserRoles();
                if (CollectionUtils.isEmpty(validUserRoleList)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                if (wondernectAuthorizeUserRoleContext.authorizeUserRole(wondernectCommonContext.getAuthorizeData().getRole(), validUserRoleList)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                break;
            }
            case REQUEST:
            {
                List<String> validUserRoleList = wondernectAuthorizeUserRoleContext.getRequestValidUserRoles(request.getRequestURI(), request.getMethod());
                if (CollectionUtils.isEmpty(validUserRoleList)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                if (wondernectAuthorizeUserRoleContext.authorizeUserRole(wondernectCommonContext.getAuthorizeData().getRole(), validUserRoleList)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                break;
            }
            default:
            {
                throw new BusinessException(BusinessError.AUTHORIZE_TYPE_IS_INVALID);
            }
        }
        return true;
    }

    private void authorizeExpiresToken(String expiresToken) {
        AuthorizeData authorizeData = wondernectAuthorizeUserRoleContext.authorizeExpiresToken(expiresToken);
        if (ESObjectUtils.isNull(authorizeData) ||
                ESStringUtils.isBlank(authorizeData.getUserId()) ||
                ESStringUtils.isBlank(authorizeData.getAppId())) {
            throw new BusinessException(BusinessError.AUTHORIZE_TOKEN_INVALID);
        }
        wondernectCommonContext.getAuthorizeData().setToken(expiresToken);
        wondernectCommonContext.getAuthorizeData().setUserId(authorizeData.getUserId());
        wondernectCommonContext.getAuthorizeData().setAppId(authorizeData.getAppId());
        wondernectCommonContext.getAuthorizeData().setRole(authorizeData.getRole());
    }

    private void authorizeUnlimitedToken(String unlimitedToken) {
        AuthorizeData authorizeData = wondernectAuthorizeUserRoleContext.authorizeUnlimitedToken(unlimitedToken);
        if (ESObjectUtils.isNull(authorizeData) ||
                ESStringUtils.isBlank(authorizeData.getUserId()) ||
                ESStringUtils.isBlank(authorizeData.getAppId())) {
            throw new BusinessException(BusinessError.AUTHORIZE_TOKEN_INVALID);
        }
        wondernectCommonContext.getAuthorizeData().setToken(unlimitedToken);
        wondernectCommonContext.getAuthorizeData().setUserId(authorizeData.getUserId());
        wondernectCommonContext.getAuthorizeData().setAppId(authorizeData.getAppId());
        wondernectCommonContext.getAuthorizeData().setRole(authorizeData.getRole());
    }
}
