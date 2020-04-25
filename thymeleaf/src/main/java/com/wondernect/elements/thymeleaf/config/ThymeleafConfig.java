package com.wondernect.elements.thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ThymeleafConfig
 * Author: chenxun
 * Date: 2019/3/2 13:58
 * Description:
 */
@Configuration
@EnableConfigurationProperties(ThymeleafConfigProperties.class)
public class ThymeleafConfig {

    /**
     * 使用@EnableConfigurationProperties(ThymeleafConfigProperties.class)使ThymeleafConfigProperties配置生效
     */
    @Autowired
    private ThymeleafConfigProperties thymeleafConfigProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public TemplateEngine stringTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(thymeleafConfigProperties.getTemplateMode());
        stringTemplateResolver.setCacheable(thymeleafConfigProperties.getCache());
        stringTemplateResolver.setCacheTTLMs(thymeleafConfigProperties.getCacheTTL());
        springTemplateEngine.setTemplateResolver(stringTemplateResolver);
        return springTemplateEngine;
    }

    @Bean
    public TemplateEngine fileTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        FileTemplateResolver fileTemplateResolver = new FileTemplateResolver();
        fileTemplateResolver.setPrefix(thymeleafConfigProperties.getPrefix());
        fileTemplateResolver.setCharacterEncoding(thymeleafConfigProperties.getCharacterEncoding());
        fileTemplateResolver.setTemplateMode(thymeleafConfigProperties.getTemplateMode());
        fileTemplateResolver.setCacheable(thymeleafConfigProperties.getCache());
        fileTemplateResolver.setCacheTTLMs(thymeleafConfigProperties.getCacheTTL());
        springTemplateEngine.setTemplateResolver(fileTemplateResolver);
        return springTemplateEngine;
    }

    @Bean
    public TemplateEngine springResourceTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setPrefix(thymeleafConfigProperties.getPrefix());
        springResourceTemplateResolver.setCharacterEncoding(thymeleafConfigProperties.getCharacterEncoding());
        springResourceTemplateResolver.setTemplateMode(thymeleafConfigProperties.getTemplateMode());
        springResourceTemplateResolver.setCacheable(thymeleafConfigProperties.getCache());
        springResourceTemplateResolver.setCacheTTLMs(thymeleafConfigProperties.getCacheTTL());
        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver);
        return springTemplateEngine;
    }

    @Bean
    public TemplateEngine urlTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        UrlTemplateResolver urlTemplateResolver = new UrlTemplateResolver();
        urlTemplateResolver.setCharacterEncoding(thymeleafConfigProperties.getCharacterEncoding());
        urlTemplateResolver.setTemplateMode(thymeleafConfigProperties.getTemplateMode());
        urlTemplateResolver.setCacheable(thymeleafConfigProperties.getCache());
        urlTemplateResolver.setCacheTTLMs(thymeleafConfigProperties.getCacheTTL());
        springTemplateEngine.setTemplateResolver(urlTemplateResolver);
        return springTemplateEngine;
    }
}
