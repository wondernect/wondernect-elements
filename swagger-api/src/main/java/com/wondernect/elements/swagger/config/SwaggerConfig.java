package com.wondernect.elements.swagger.config;

import com.wondernect.elements.common.utils.ESStringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SwaggerConfig
 * Author: chenxun
 * Date: 2019/4/29 9:15
 * Description:
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfigProperties.class)
public class SwaggerConfig {

    @Autowired
    private SwaggerConfigProperties swaggerConfigProperties;

    @Bean
    public Docket create() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerConfigProperties.isEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(ESStringUtils.isBlank(swaggerConfigProperties.getPathRegex()) ? PathSelectors.any() : PathSelectors.regex(swaggerConfigProperties.getPathRegex()))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerConfigProperties.getTitle())
                .termsOfServiceUrl(swaggerConfigProperties.getServiceUrl())
                .contact(new Contact(swaggerConfigProperties.getContactName(), swaggerConfigProperties.getContactUrl(), swaggerConfigProperties.getContactEmail()))
                .description(swaggerConfigProperties.getDescription())
                .version(swaggerConfigProperties.getVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey(swaggerConfigProperties.getAppIdPropertyName(), swaggerConfigProperties.getAppIdPropertyName(), swaggerConfigProperties.getAppIdPassAs()));
        apiKeyList.add(new ApiKey(swaggerConfigProperties.getAppSecretPropertyName(), swaggerConfigProperties.getAppSecretPropertyName(), swaggerConfigProperties.getAppSecretPassAs()));
        apiKeyList.add(new ApiKey(swaggerConfigProperties.getUserIdPropertyName(), swaggerConfigProperties.getUserIdPropertyName(), swaggerConfigProperties.getUserIdPassAs()));
        apiKeyList.add(new ApiKey(swaggerConfigProperties.getAuthorizationPropertyName(), swaggerConfigProperties.getAuthorizationPropertyName(), swaggerConfigProperties.getAuthorizationPassAs()));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "global access");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        securityReferenceList.add(new SecurityReference(swaggerConfigProperties.getAppIdPropertyName(), authorizationScopes));
        securityReferenceList.add(new SecurityReference(swaggerConfigProperties.getAppSecretPropertyName(), authorizationScopes));
        securityReferenceList.add(new SecurityReference(swaggerConfigProperties.getUserIdPropertyName(), authorizationScopes));
        securityReferenceList.add(new SecurityReference(swaggerConfigProperties.getAuthorizationPropertyName(), authorizationScopes));

        List<SecurityContext> securityContextList = new ArrayList<>();
        securityContextList.add(
                SecurityContext.builder()
                        .securityReferences(securityReferenceList)
                        .forPaths(ESStringUtils.isBlank(swaggerConfigProperties.getPathRegex()) ? PathSelectors.any() : PathSelectors.regex(swaggerConfigProperties.getPathRegex()))
                        .build()
        );
        return securityContextList;
    }
}
