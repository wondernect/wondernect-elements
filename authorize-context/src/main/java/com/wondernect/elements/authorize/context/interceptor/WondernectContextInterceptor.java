package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.config.WondernectAuthorizeContextConfigProperties;
import com.wondernect.elements.authorize.context.config.WondernectCommonContextConfigProperties;
import com.wondernect.elements.authorize.context.config.WondernectCorsContextConfigProperties;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: WondernectContextInterceptor
 * Author: chenxun
 * Date: 2019/3/14 11:13
 * Description: wondernect context 拦截器
 */
@Configuration
@EnableConfigurationProperties({
        WondernectCorsContextConfigProperties.class,
        WondernectCommonContextConfigProperties.class,
        WondernectAuthorizeContextConfigProperties.class
})
public class WondernectContextInterceptor implements WebMvcConfigurer {

    @Autowired
    private WondernectCorsContextConfigProperties wondernectCorsContextConfigProperties;

    @Autowired
    private WondernectCommonContextConfigProperties wondernectCommonContextConfigProperties;

    @Autowired
    private WondernectAuthorizeContextConfigProperties wondernectAuthorizeContextConfigProperties;

    @Bean
    public WondernectCorsHandlerInterceptor wondernectCorsHandlerInterceptor() {
        if (wondernectCorsContextConfigProperties.isEnable()) {
            return new WondernectCorsHandlerInterceptor();
        }
        return null;
    }

    @Bean
    public WondernectCommonHandlerInterceptor wondernectCommonHandlerInterceptor() {
        if (wondernectCommonContextConfigProperties.isEnable()) {
            return new WondernectCommonHandlerInterceptor();
        }
        return null;
    }

    @Bean
    public WondernectAuthorizeHandlerInterceptor wondernectAuthorizeHandlerInterceptor() {
        if (wondernectAuthorizeContextConfigProperties.isEnable()) {
            return new WondernectAuthorizeHandlerInterceptor();
        }
        return null;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (wondernectCorsContextConfigProperties.isEnable()) {
            if (CollectionUtils.isNotEmpty(wondernectCorsContextConfigProperties.getPathPatterns())) {
                if (CollectionUtils.isNotEmpty(wondernectCorsContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectCorsHandlerInterceptor())
                            .addPathPatterns(wondernectCorsContextConfigProperties.getPathPatterns())
                            .excludePathPatterns(wondernectCorsContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectCorsHandlerInterceptor())
                            .addPathPatterns(wondernectCorsContextConfigProperties.getPathPatterns());
                }
            } else {
                if (CollectionUtils.isNotEmpty(wondernectCorsContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectCorsHandlerInterceptor())
                            .addPathPatterns("/**")
                            .excludePathPatterns(wondernectCorsContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectCorsHandlerInterceptor()).addPathPatterns("/**");
                }
            }
        }
        if (wondernectCommonContextConfigProperties.isEnable()) {
            if (CollectionUtils.isNotEmpty(wondernectCommonContextConfigProperties.getPathPatterns())) {
                if (CollectionUtils.isNotEmpty(wondernectCommonContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectCommonHandlerInterceptor())
                            .addPathPatterns(wondernectCommonContextConfigProperties.getPathPatterns())
                            .excludePathPatterns(wondernectCommonContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectCommonHandlerInterceptor())
                            .addPathPatterns(wondernectCommonContextConfigProperties.getPathPatterns());
                }
            } else {
                if (CollectionUtils.isNotEmpty(wondernectCommonContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectCommonHandlerInterceptor())
                            .addPathPatterns("/**")
                            .excludePathPatterns(wondernectCommonContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectCommonHandlerInterceptor()).addPathPatterns("/**");
                }
            }
        }
        if (wondernectAuthorizeContextConfigProperties.isEnable()) {
            if (CollectionUtils.isNotEmpty(wondernectAuthorizeContextConfigProperties.getPathPatterns())) {
                if (CollectionUtils.isNotEmpty(wondernectAuthorizeContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns(wondernectAuthorizeContextConfigProperties.getPathPatterns())
                            .excludePathPatterns(wondernectAuthorizeContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns(wondernectAuthorizeContextConfigProperties.getPathPatterns());
                }
            } else {
                if (CollectionUtils.isNotEmpty(wondernectAuthorizeContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns("/**")
                            .excludePathPatterns(wondernectAuthorizeContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns("/**");
                }
            }
        }
    }
}
