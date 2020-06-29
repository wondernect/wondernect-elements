package com.wondernect.elements.authorize.context.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectContextConfig
 * Author: chenxun
 * Date: 2019/3/27 9:12
 * Description: wondernect context config
 */
@Configuration
@EnableConfigurationProperties({
        WondernectCorsContextConfigProperties.class,
        WondernectCommonContextConfigProperties.class,
        WondernectUserRoleContextConfigProperties.class,
        WondernectServerContextConfigProperties.class
})
public class WondernectContextConfig {

    /**
     * 使用@EnableConfigurationProperties使WondernectCorsContextConfigProperties,WondernectCommonContextConfigProperties,WondernectAuthorizeContextConfigProperties配置生效
     */
}
