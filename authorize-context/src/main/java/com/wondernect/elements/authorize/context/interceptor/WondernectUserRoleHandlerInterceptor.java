package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.WondernectAuthorizeContext;
import com.wondernect.elements.authorize.context.config.WondernectUserRoleContextConfigProperties;
import com.wondernect.elements.authorize.context.impl.DefaultWondernectCommonContext;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
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
    private WondernectUserRoleContextConfigProperties wondernectAuthorizeContextConfigProperties;

    @Autowired
    private WondernectAuthorizeContext wondernectAuthorizeContext;

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
        AuthorizeType authorizeType = authorizeUserRole.authorizeType();
        String expiresToken;
        String unlimitedToken;
        switch (authorizeType) {
            case EXPIRES_TOKEN:
            {
                expiresToken = request.getHeader(wondernectAuthorizeContextConfigProperties.getExpiresTokenPropertyName());
                if (ESStringUtils.isRealEmpty(expiresToken)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_HEADER_IS_NULL);
                }
                authorizeExpiresToken(expiresToken);
                break;
            }
            case UNLIMITED_TOKEN:
            {
                unlimitedToken = request.getHeader(wondernectAuthorizeContextConfigProperties.getUnlimitedTokenPropertyName());
                if (ESStringUtils.isRealEmpty(unlimitedToken)) {
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
        AuthorizeRoleType authorizeRoleType = authorizeUserRole.authorizeRoleType();
        switch (authorizeRoleType) {
            case ONLY_AUTHORIZE:
            {
                getUserRole();
                break;
            }
            case CONFIG:
            {
                if (ArrayUtils.isNotEmpty(authorizeUserRole.validUserRoles())) {
                    if (!wondernectAuthorizeContext.authorizeUserRole(getUserRole(), Arrays.asList(authorizeUserRole.validUserRoles()))) {
                        throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                    }
                } else {
                    if (CollectionUtils.isEmpty(wondernectAuthorizeContextConfigProperties.getLocalAuthValidRoles())) {
                        logger.error("wondernectAuthorizeContextConfigProperties local auth valid roles is null !!!");
                        throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                    }
                    if (!wondernectAuthorizeContext.authorizeUserRole(getUserRole(), wondernectAuthorizeContextConfigProperties.getLocalAuthValidRoles())) {
                        throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                    }
                }
                break;
            }
            case CUSTOM:
            {
                if (!wondernectAuthorizeContext.authorizeUserRole(getUserRole(), wondernectAuthorizeContext.getCustomValidUserRoles())) {
                    throw new BusinessException(BusinessError.AUTHORIZE_USER_ROLE_INVALID);
                }
                break;
            }
            case REQUEST:
            {
                if (!wondernectAuthorizeContext.authorizeUserRole(getUserRole(), wondernectAuthorizeContext.getRequestValidUserRoles(request.getRequestURI(), request.getMethod()))) {
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
        String userId = wondernectAuthorizeContext.authorizeExpiresToken(expiresToken);
        if (ESStringUtils.isRealEmpty(userId)) {
            throw new BusinessException(BusinessError.AUTHORIZE_TOKEN_INVALID);
        }
        wondernectCommonContext.getAuthorizeData().setToken(expiresToken);
        wondernectCommonContext.getAuthorizeData().setUserId(userId);
    }

    private void authorizeUnlimitedToken(String unlimitedToken) {
        String userId = wondernectAuthorizeContext.authorizeUnlimitedToken(unlimitedToken);
        if (ESStringUtils.isRealEmpty(userId)) {
            throw new BusinessException(BusinessError.AUTHORIZE_TOKEN_INVALID);
        }
        wondernectCommonContext.getAuthorizeData().setToken(unlimitedToken);
        wondernectCommonContext.getAuthorizeData().setUserId(userId);
    }

    private String getUserRole() {
        String userRole = wondernectAuthorizeContext.getUserRole(wondernectCommonContext.getAuthorizeData().getUserId());
        wondernectCommonContext.getAuthorizeData().setRole(userRole);
        return userRole;
    }
}
