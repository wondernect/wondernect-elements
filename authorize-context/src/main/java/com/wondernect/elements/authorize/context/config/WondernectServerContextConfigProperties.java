package com.wondernect.elements.authorize.context.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectContextConfigProperties
 * Author: chenxun
 * Date: 2019/3/27 9:12
 * Description: wondernect context config properties
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.elements.context.authorize.server")
public class WondernectServerContextConfigProperties implements Serializable {

    private static final long serialVersionUID = 7791441442439623552L;

    private boolean enable = false; // 是否启用应用认证

    private String userPropertyName = "USERID"; // 用户标识

    private String appPropertyName = "APPID"; // 应用标识

    private String encryptSecretPropertyName = "APPSECRET"; // 传递加密内容的头部key

    private List<String> pathPatterns; // 过滤路径

    private List<String> excludePathPatterns; // 排除路径

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUserPropertyName() {
        return userPropertyName;
    }

    public void setUserPropertyName(String userPropertyName) {
        this.userPropertyName = userPropertyName;
    }

    public String getAppPropertyName() {
        return appPropertyName;
    }

    public void setAppPropertyName(String appPropertyName) {
        this.appPropertyName = appPropertyName;
    }

    public String getEncryptSecretPropertyName() {
        return encryptSecretPropertyName;
    }

    public void setEncryptSecretPropertyName(String encryptSecretPropertyName) {
        this.encryptSecretPropertyName = encryptSecretPropertyName;
    }

    public List<String> getPathPatterns() {
        return pathPatterns;
    }

    public void setPathPatterns(List<String> pathPatterns) {
        this.pathPatterns = pathPatterns;
    }

    public List<String> getExcludePathPatterns() {
        return excludePathPatterns;
    }

    public void setExcludePathPatterns(List<String> excludePathPatterns) {
        this.excludePathPatterns = excludePathPatterns;
    }
}
