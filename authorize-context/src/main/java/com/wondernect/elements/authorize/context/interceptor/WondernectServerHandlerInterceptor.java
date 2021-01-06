package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.WondernectAuthorizeServerContext;
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
    private WondernectAuthorizeServerContext wondernectAuthorizeServerContext;

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
        AuthorizeServer authorizeServer = method.getAnnotation(AuthorizeServer.class);
        if (null != authorizeServer) {
            // 获取requestId
            String requestId = request.getHeader(wondernectServerContextConfigProperties.getRequestPropertyName());
            if (ESStringUtils.isNotBlank(requestId)) {
                wondernectCommonContext.setRequestId(requestId);
            }
            // 获取appId
            String appId = request.getHeader(wondernectServerContextConfigProperties.getAppPropertyName());
            if (ESStringUtils.isBlank(appId)) {
                throw new BusinessException(BusinessError.AUTHORIZE_APPID_IS_NULL);
            }
            wondernectCommonContext.getAuthorizeData().setAppId(appId);
            // 获取userId
            String userId = request.getHeader(wondernectServerContextConfigProperties.getUserPropertyName());
            if (ESStringUtils.isBlank(userId)) {
                throw new BusinessException(BusinessError.AUTHORIZE_USERID_IS_NULL);
            }
            wondernectCommonContext.getAuthorizeData().setUserId(userId);
            // 验证应用访问权限
            AuthorizeAccessType authorizeAccessType = authorizeServer.accessType();
            if (!wondernectAuthorizeServerContext.authorizeAppAccessType(wondernectAuthorizeServerContext.getAppAccessType(appId, userId), authorizeAccessType)) {
                throw new BusinessException(BusinessError.AUTHORIZE_APP_ACCESS_TYPE_INVALID);
            }
            // 独立部署应用验证app密钥是否合法(后期会根据具体需求做调整)
            if (wondernectServerContextConfigProperties.isStandAlone()) {
                String appSecret = request.getHeader(wondernectServerContextConfigProperties.getAppSecretPropertyName());
                if (ESStringUtils.isBlank(appSecret)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_APP_SECRET_IS_NULL);
                }
                if (!wondernectAuthorizeServerContext.authorizeStandAloneAppSecret(appId, appSecret, userId)) {
                    throw new BusinessException(BusinessError.AUTHORIZE_APP_SECRET_INVALID);
                }
            }
        }
        return true;
    }
}
