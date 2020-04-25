package com.wondernect.elements.authorize.context.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectCommonContextConfigProperties
 * Author: chenxun
 * Date: 2019/3/27 13:53
 * Description: wondernect cors context config properties
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "wondernect.elements.context.cors")
public class WondernectCorsContextConfigProperties implements Serializable {

    private static final long serialVersionUID = -8973514319049648652L;

    private boolean enable = false; // 是否启用

    private List<String> pathPatterns; // 过滤路径

    private List<String> excludePathPatterns; // 排除路径

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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
