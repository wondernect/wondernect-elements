package com.wondernect.elements.jdbc.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SMSClientConfig
 * Author: chenxun
 * Date: 2018/11/27 10:54
 * Description:
 */
@Configuration
@EnableConfigurationProperties(
        value = {
                JDBCClientConfigProperties.class
        }
)
public class JDBCClientConfig {

    /**
     * 该config使用@EnableConfigurationProperties(JDBCClientConfigProperties.class)使JDBCClientConfigProperties配置生效
     */
}
