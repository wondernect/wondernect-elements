package com.wondernect.elements.authorize.context.interceptor;

import com.wondernect.elements.authorize.context.config.WondernectServerContextConfigProperties;
import com.wondernect.elements.authorize.context.config.WondernectUserRoleContextConfigProperties;
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
        WondernectUserRoleContextConfigProperties.class,
        WondernectServerContextConfigProperties.class
})
public class WondernectContextInterceptor implements WebMvcConfigurer {

    @Autowired
    private WondernectCorsContextConfigProperties wondernectCorsContextConfigProperties;

    @Autowired
    private WondernectCommonContextConfigProperties wondernectCommonContextConfigProperties;

    @Autowired
    private WondernectUserRoleContextConfigProperties wondernectUserRoleContextConfigProperties;

    @Autowired
    private WondernectServerContextConfigProperties wondernectServerContextConfigProperties;

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
    public WondernectUserRoleHandlerInterceptor wondernectAuthorizeHandlerInterceptor() {
        if (wondernectUserRoleContextConfigProperties.isEnable()) {
            return new WondernectUserRoleHandlerInterceptor();
        }
        return null;
    }

    public WondernectServerHandlerInterceptor wondernectServerHandlerInterceptor() {
        if (wondernectServerContextConfigProperties.isEnable()) {
            return new WondernectServerHandlerInterceptor();
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
        if (wondernectUserRoleContextConfigProperties.isEnable()) {
            if (CollectionUtils.isNotEmpty(wondernectUserRoleContextConfigProperties.getPathPatterns())) {
                if (CollectionUtils.isNotEmpty(wondernectUserRoleContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns(wondernectUserRoleContextConfigProperties.getPathPatterns())
                            .excludePathPatterns(wondernectUserRoleContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns(wondernectUserRoleContextConfigProperties.getPathPatterns());
                }
            } else {
                if (CollectionUtils.isNotEmpty(wondernectUserRoleContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns("/**")
                            .excludePathPatterns(wondernectUserRoleContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns("/**");
                }
            }
        }
        if (wondernectServerContextConfigProperties.isEnable()) {
            if (CollectionUtils.isNotEmpty(wondernectServerContextConfigProperties.getPathPatterns())) {
                if (CollectionUtils.isNotEmpty(wondernectServerContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns(wondernectServerContextConfigProperties.getPathPatterns())
                            .excludePathPatterns(wondernectServerContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns(wondernectServerContextConfigProperties.getPathPatterns());
                }
            } else {
                if (CollectionUtils.isNotEmpty(wondernectServerContextConfigProperties.getExcludePathPatterns())) {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns("/**")
                            .excludePathPatterns(wondernectServerContextConfigProperties.getExcludePathPatterns());
                } else {
                    registry.addInterceptor(wondernectAuthorizeHandlerInterceptor())
                            .addPathPatterns("/**");
                }
            }
        }
    }
}
