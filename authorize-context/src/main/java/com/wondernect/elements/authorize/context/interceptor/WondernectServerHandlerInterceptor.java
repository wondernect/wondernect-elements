package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.WondernectAuthorizeContext;
import com.wondernect.elements.authorize.context.config.WondernectServerContextConfigProperties;
import com.wondernect.elements.authorize.context.impl.DefaultWondernectCommonContext;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectAuthorizeContextInterceptor
 * Author: chenxun
 * Date: 2019/3/27 13:34
 * Description:
 */
public class WondernectServerHandlerInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(WondernectServerHandlerInterceptor.class);

    @Autowired
    private WondernectServerContextConfigProperties wondernectServerContextConfigProperties;

    @Autowired
    private WondernectAuthorizeContext wondernectAuthorizeContext;

    @Autowired
    private DefaultWondernectCommonContext wondernectCommonContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!wondernectServerContextConfigProperties.isEnable()) {
            return true;
        }
        if (!(handler instanceof HandlerMethod)) {
            throw new BusinessException(BusinessError.INVALID_REQUEST_URL);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AuthorizeServer authorizeAppSecret = method.getAnnotation(AuthorizeServer.class);
        if (null != authorizeAppSecret) {
            String appId = request.getHeader(wondernectServerContextConfigProperties.getAppPropertyName());
            if (ESStringUtils.isBlank(appId)) {
                throw new BusinessException(BusinessError.AUTHORIZE_APPID_IS_NULL);
            }
            wondernectCommonContext.getAuthorizeData().setAppId(appId);
            String userId = request.getHeader(wondernectServerContextConfigProperties.getUserPropertyName());
            if (ESStringUtils.isNotBlank(userId)) {
                wondernectCommonContext.getAuthorizeData().setUserId(userId);
            }
            String encryptSecret = request.getHeader(wondernectServerContextConfigProperties.getEncryptSecretPropertyName());
            if (ESStringUtils.isBlank(encryptSecret)) {
                throw new BusinessException(BusinessError.AUTHORIZE_APP_SECRET_IS_NULL);
            }
            if (!wondernectAuthorizeContext.authorizeAppSecret(appId, encryptSecret)) {
                throw new BusinessException(BusinessError.AUTHORIZE_APP_SECRET_INVALID);
            }
        }
        return true;
    }
}
