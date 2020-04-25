package com.wondernect.elements.sms.client.config;

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
                AliyunSMSConfigProperties.class,
                JDSMSConfigProperties.class
        }
)
public class SMSClientConfig {

    /**
     * 该config使用@EnableConfigurationProperties(SMSClientConfigProperties.class)使SMSClientConfigProperties配置生效
     */
}
