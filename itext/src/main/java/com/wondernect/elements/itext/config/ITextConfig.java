package com.wondernect.elements.itext.config;

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
@EnableConfigurationProperties(ITextConfigProperties.class)
public class ITextConfig {

    /**
     * 该config使用@EnableConfigurationProperties(MailClientConfigProperties.class)使ITextConfigProperties配置生效
     */
}
