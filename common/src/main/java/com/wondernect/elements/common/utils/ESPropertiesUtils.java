package com.wondernect.elements.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: ESPropertiesLoaderUtils
 * Author: chenxun
 * Date: 2018/11/21 13:50
 * Description:
 */
public class ESPropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESPropertiesUtils.class);

    private static final Map<String, Properties> propertiesHashMap = new HashMap<>();

    private static Properties loadProperties(String resourceName, boolean cacheable) {
        Properties properties = null;
        ClassPathResource classPathResource = new ClassPathResource(resourceName);
        try {
            properties = PropertiesLoaderUtils.loadProperties(classPathResource);
            if (cacheable) {
                propertiesHashMap.put(resourceName, properties);
            }
        } catch (IOException e) {
            logger.error("ESPropertiesUtils load failed", e);
        }
        return properties;
    }

    public static String getProperty(String resourceName, String key, String defaultValue) {
        Properties properties = propertiesHashMap.get(resourceName);
        if (null == properties) {
            properties = loadProperties(resourceName, true);
        }
        if (null == properties) {
            return defaultValue;
        }
        return properties.getProperty(key, defaultValue);
    }

    public static String getPropertyWithoutCache(String resourceName, String key, String defaultValue) {
        Properties properties = loadProperties(resourceName, false);
        if (null == properties) {
            return defaultValue;
        }
        return properties.getProperty(key, defaultValue);
    }
}
