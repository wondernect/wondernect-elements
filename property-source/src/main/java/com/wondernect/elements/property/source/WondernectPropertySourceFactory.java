package com.wondernect.elements.property.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: WondernectPropertySourceFactory
 * Author: chenxun
 * Date: 2020-04-24 20:35
 * Description:
 */
public class WondernectPropertySourceFactory extends DefaultPropertySourceFactory {

    private static final Logger logger = LoggerFactory.getLogger(WondernectPropertySourceFactory.class);
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource.getResource().exists()) {
            if (name == null || "".equals(name)) {
                name = resource.getResource().getFilename();
            }
            logger.info("property source read:{}, {}", name, resource);
            if (name.endsWith(".yml") || name.endsWith(".yaml")) {
                Properties propertiesFromYaml = loadYml(resource);
                return new PropertiesPropertySource(name, propertiesFromYaml);
            } else {
                return super.createPropertySource(name, resource);
            }
        } else {
            if (name == null || "".equals(name)) {
                name = "unknown";
            }
            return new PropertiesPropertySource(name, new Properties());
        }
    }

    private Properties loadYml(EncodedResource resource) {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
