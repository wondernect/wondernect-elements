package com.wondernect.elements.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
     * java反射bean的get方法
     */
    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        Method method = null;
        try {
            method = objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            logger.error("获取class get方法异常", e);
        }
        return method;
    }

    /**
     * java反射bean的set方法
     */
    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("set");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        Method method = null;
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            method = objectClass.getMethod(sb.toString(), parameterTypes);
        } catch (Exception e) {
            logger.error("获取class set方法异常", e);
        }
        return method;
    }

    /**
     * 执行set方法
     */
    public static void invokeSetMethod(Object object, String fieldName, Object value) {
        Method method = getSetMethod(object.getClass(), fieldName);
        try {
            method.invoke(object, value);
        } catch (Exception e) {
            logger.error("执行class set方法异常", e);
        }
    }

    /**
     * 执行get方法
     */
    public static Object invokeGetMethod(Object object, String fieldName) {
        Method method = getGetMethod(object.getClass(), fieldName);
        Object value = null;
        try {
            value = method.invoke(object);
        } catch (Exception e) {
            logger.error("执行class get方法异常", e);
        }
        return value;
    }
}
