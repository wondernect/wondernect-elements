package com.wondernect.elements.authorize.context.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
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
@ConfigurationProperties(prefix = "wondernect.elements.context.authorize")
public class WondernectUserRoleContextConfigProperties implements Serializable {

    private static final long serialVersionUID = 6900635498606055034L;

    private boolean enable = false; // 是否启用

    private String expiresTokenPropertyName = "Authorization"; // 头部认证信息标识

    private String unlimitedTokenPropertyName = "Authorization"; // 头部认证信息标识

    private List<String> pathPatterns; // 过滤路径

    private List<String> excludePathPatterns; // 排除路径

    private List<String> localAuthValidRoles = new ArrayList<>(); // 本地角色验证配置

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getExpiresTokenPropertyName() {
        return expiresTokenPropertyName;
    }

    public void setExpiresTokenPropertyName(String expiresTokenPropertyName) {
        this.expiresTokenPropertyName = expiresTokenPropertyName;
    }

    public String getUnlimitedTokenPropertyName() {
        return unlimitedTokenPropertyName;
    }

    public void setUnlimitedTokenPropertyName(String unlimitedTokenPropertyName) {
        this.unlimitedTokenPropertyName = unlimitedTokenPropertyName;
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

    public List<String> getLocalAuthValidRoles() {
        return localAuthValidRoles;
    }

    public void setLocalAuthValidRoles(List<String> localAuthValidRoles) {
        this.localAuthValidRoles = localAuthValidRoles;
    }
}
