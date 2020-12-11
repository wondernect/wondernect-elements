package com.wondernect.elements.feign.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: WondernectFeignConfigProperties
 * Author: chenxun
 * Date: 2018/5/25 10:37
 * Description: file feign config
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.stars.feign")
public class WondernectFeignConfigProperties implements Serializable {

    private static final long serialVersionUID = -8120698783277706219L;

    private String requestIdPropertyName = "REQUESTID"; // 请求标识

    private String userIdPropertyName = "USERID"; // 用户标识

    private String appIdPropertyName = "APPID"; // 应用标识

    private String appId; // 应用id

    private String appSecretPropertyName = "APPSECRET"; // 传递加密内容的头部key

    private String appSecret; // 密钥

    public String getRequestIdPropertyName() {
        return requestIdPropertyName;
    }

    public void setRequestIdPropertyName(String requestIdPropertyName) {
        this.requestIdPropertyName = requestIdPropertyName;
    }

    public String getUserIdPropertyName() {
        return userIdPropertyName;
    }

    public void setUserIdPropertyName(String userIdPropertyName) {
        this.userIdPropertyName = userIdPropertyName;
    }

    public String getAppIdPropertyName() {
        return appIdPropertyName;
    }

    public void setAppIdPropertyName(String appIdPropertyName) {
        this.appIdPropertyName = appIdPropertyName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecretPropertyName() {
        return appSecretPropertyName;
    }

    public void setAppSecretPropertyName(String appSecretPropertyName) {
        this.appSecretPropertyName = appSecretPropertyName;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}

