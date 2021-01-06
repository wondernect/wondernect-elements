package com.wondernect.elements.feign.config;

import com.wondernect.elements.common.utils.ESObjectUtils;
import feign.RequestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: AbstractWondernectFeignHeaderContext
 * Author: chenxun
 * Date: 2020/12/25 9:42
 * Description: feign头部扩展信息抽象实现
 */
public abstract class AbstractWondernectFeignHeaderContext implements WondernectFeignHeaderContext {

    @Override
    public void extendFeignHeader(RequestTemplate requestTemplate, HttpServletRequest request, WondernectFeignHeaderConfigProperties wondernectFeignHeaderConfigProperties) {
        Object requestId = request.getAttribute(wondernectFeignHeaderConfigProperties.getRequestIdPropertyName());
        if (ESObjectUtils.isNotNull(requestId)) {
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getRequestIdPropertyName(), requestId.toString());
        }
        Object userId = request.getAttribute(wondernectFeignHeaderConfigProperties.getUserIdPropertyName());
        if (ESObjectUtils.isNotNull(userId)) {
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getUserIdPropertyName(), userId.toString());
        } else {
            // 默认userId配置
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getUserIdPropertyName(), wondernectFeignHeaderConfigProperties.getUserId());
        }
        Object appId = request.getAttribute(wondernectFeignHeaderConfigProperties.getAppIdPropertyName());
        if (ESObjectUtils.isNotNull(appId)) {
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getAppIdPropertyName(), appId.toString());
        } else {
            // 默认appId配置
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getAppIdPropertyName(), wondernectFeignHeaderConfigProperties.getAppId());
        }
        Object appSecret = request.getAttribute(wondernectFeignHeaderConfigProperties.getAppSecretPropertyName());
        if (ESObjectUtils.isNotNull(appSecret)) {
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getAppSecretPropertyName(), appSecret.toString());
        } else {
            // 默认appSecret配置
            requestTemplate.header(wondernectFeignHeaderConfigProperties.getAppSecretPropertyName(), wondernectFeignHeaderConfigProperties.getAppSecret());
        }
    }
}
