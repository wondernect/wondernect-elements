package com.wondernect.elements.authorize.context.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectCorsHandlerInterceptor
 * Author: chenxun
 * Date: 2019/3/14 10:53
 * Description: wondernect request interceptor
 */
public class WondernectCorsHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 此处配置的是允许任意域名跨域请求，可根据需求指定
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            // System.out.println("OPTIONS=>跨域拦截器=>" + request.getMethod() + ":" + ESHttpUtils.getHttpRequestUrl(request));
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        return true;
    }
}
