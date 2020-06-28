package com.wondernect.elements.common.utils;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
