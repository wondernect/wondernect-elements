package com.wondernect.elements.mail.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: MailClientConfig
 * Author: chenxun
 * Date: 2018/11/27 10:34
 * Description:
 */
@Configuration
@EnableConfigurationProperties(MailClientConfigProperties.class)
public class MailClientConfig {

    /**
     * 该config使用@EnableConfigurationProperties(MailClientConfigProperties.class)使MailClientConfigProperties配置生效
     */
}
