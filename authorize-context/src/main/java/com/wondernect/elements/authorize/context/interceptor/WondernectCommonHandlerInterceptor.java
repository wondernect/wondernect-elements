package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.impl.DefaultWondernectCommonContext;
import com.wondernect.elements.common.utils.ESHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectCommonHandlerInterceptor
 * Author: chenxun
 * Date: 2019/3/14 10:53
 * Description: wondernect request interceptor
 */
public class WondernectCommonHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private DefaultWondernectCommonContext wondernectCommonContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        wondernectCommonContext.setRequestUrl(ESHttpUtils.getHttpRequestURI(request));
        wondernectCommonContext.setRequestIp(ESHttpUtils.getHttpRequestIP(request));
        wondernectCommonContext.setRequestDevice(ESHttpUtils.getHttpRequestUserAgent(request));
        wondernectCommonContext.setDevicePlatform(ESHttpUtils.getDevicePlatform(request));
        wondernectCommonContext.setDeviceIdentifier(ESHttpUtils.getDeviceIdentifier(request));
        return true;
    }
}
