package com.wondernect.elements.common.utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESClassUtils
 * Author: chenxun
 * Date: 2020-06-26 21:45
 * Description:
 */
public final class ESClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ESClassUtils.class);

    /**
     * 根据类名获取类
     */
    public static Class<?> getClassByName(String className) {
        Class<?> cls = null;
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("class name not found, name is " + className, e);
        }
        return cls;
    }

    /**
     * 获取类的所有属性
     */
    public static List<Field> getAllFieldsList(Class<?> cls) {
        return FieldUtils.getAllFieldsList(cls);
    }

    /**
     * 获取类的属性类型
     */
    public static String getFieldType(Field field) {
        return field.getGenericType().getTypeName();
    }

    /**
     * 获取类的属性名
     */
    public static String getFieldName(Field field) {
        return field.getName();
    }

    /**
     * 获取属性注解
     */
    public static <T extends Annotation> T getFieldAnnotation(Field field, Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    /**
     * 获取类的所有使用注解的属性
     */
    public static List<Field> getFieldsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        return FieldUtils.getFieldsListWithAnnotation(cls, annotationCls);
    }

    /**
     * 获取get方法
     */
    public static Method getGetMethod(Class<?> cls, String fieldName, Class... parameterTypes) {
        return MethodUtils.getMatchingMethod(cls, fieldName, parameterTypes);
    }

    /**
     * 获取get方法
     */
    public static Method getGetMethod(Object target, String fieldName, Class... parameterTypes) {
        return MethodUtils.getMatchingMethod(target.getClass(), fieldName, parameterTypes);
    }

    /**
     * 执行get方法
     */
    public static Object invokeGetMethod(Object target, Method method, Object... args) {
        Object value = null;
        try {
            value = method.invoke(target, args);
        } catch (Exception e) {
            logger.error("调用get method接口异常", e);
        }
        return value;
    }

    /**
     * 获取set方法
     */
    public static Method getSetMethod(Class<?> cls, String fieldName, Class... parameterTypes) {
        return MethodUtils.getMatchingMethod(cls, fieldName, parameterTypes);
    }

    /**
     * 获取set方法
     */
    public static Method getSetMethod(Object target, String fieldName, Class... parameterTypes) {
        return MethodUtils.getMatchingMethod(target.getClass(), fieldName, parameterTypes);
    }

    /**
     * 执行set方法
     */
    public static void invokeSetMethod(Object target, Method method, Object... args) {
        try {
            method.invoke(target, args);
        } catch (Exception e) {
            logger.error("调用set method接口异常", e);
        }
    }
}
