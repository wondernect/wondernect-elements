package com.wondernect.elements.feign.config;

import feign.RequestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: WondernectFeignHeaderContext
 * Author: chenxun
 * Date: 2020/12/25 9:38
 * Description: feign头部扩展信息
 */
public interface WondernectFeignHeaderContext {

    void extendFeignHeader(RequestTemplate requestTemplate, HttpServletRequest request, WondernectFeignHeaderConfigProperties wondernectFeignHeaderConfigProperties);
}
