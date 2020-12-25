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
        }
        requestTemplate.header(wondernectFeignHeaderConfigProperties.getAppIdPropertyName(), wondernectFeignHeaderConfigProperties.getAppId());
        requestTemplate.header(wondernectFeignHeaderConfigProperties.getAppSecretPropertyName(), wondernectFeignHeaderConfigProperties.getAppSecret());
    }
}
