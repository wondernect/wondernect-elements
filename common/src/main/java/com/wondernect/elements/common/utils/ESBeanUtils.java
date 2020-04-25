package com.wondernect.elements.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESBeanUtils
 * Author: chenxun
 * Date: 2019/6/13 18:49
 * Description:
 */
public final class ESBeanUtils {

    /**
     * source中的非空属性复制到target中
     * @param source 源对象
     * @param target 目标对象
     * @param ignoreProperties 忽略属性
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * source中的非空属性复制到target中，但是添加另外一些业务需要忽略的属性
     * @param source 源对象
     * @param target 目标对象
     * @param ignoreProperties 忽略属性
     */
    public static void copyWithoutNullAndIgnoreProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, getNullAndIgnoreProperties(source, ignoreProperties));
    }

    /**
     * source中的非空属性复制到target中，但是指定的属性不被忽略
     * @param source 源对象
     * @param target 目标对象
     * @param excludeProperties 忽略属性
     */
    public static void copyWithoutNullAndExcludeProperties(Object source, Object target, String... excludeProperties) {
        BeanUtils.copyProperties(source, target, getNullAndExcludeProperties(source, excludeProperties));
    }

    public static String[] getNullAndIgnoreProperties(Object source, String... ignoreProperties) {
        Set<String> nullProperties = getNullProperties(source);
        if (ignoreProperties.length > 0) {
            nullProperties.addAll(Arrays.asList(ignoreProperties));
        }
        String[] result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }

    public static String[] getNullAndExcludeProperties(Object source, String... excludeProperties) {
        Set<String> nullProperties = getNullProperties(source);
        if (excludeProperties.length > 0) {
            nullProperties.removeAll(Arrays.asList(excludeProperties));
        }
        String[] result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }

    public static Set<String> getNullProperties(Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> nullProperties = new HashSet<>();
        if (propertyDescriptors.length > 0) {
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getName();
                Object value = beanWrapper.getPropertyValue(name);
                if (Objects.isNull(value)) {
                    nullProperties.add(name);
                }
            }
        }
        return nullProperties;
    }
}
