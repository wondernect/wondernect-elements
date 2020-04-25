package com.wondernect.elements.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESCollectionUtils
 * Author: chenxun
 * Date: 2019/5/19 10:30
 * Description:
 */

public final class ESCollectionUtils {

    /**
     * List<T> 转 T[]
     * arrayType 一般设置为 new T[0]
     */
    public static <T> T[] toArray(List<T> list, T[] arrayType) {
        return list.toArray(arrayType);
    }

    /**
     * List<T> 转 T[] (去除 null & blank)
     * arrayType 一般设置为 new T[0]
     */
    public static <T> T[] toArrayExcludeNull(List<T> list, T[] arrayType) {
        List<T> excludeNullList = new ArrayList<>();
        for (T value : list) {
            if (ESObjectUtils.isNotNull(value)) {
                excludeNullList.add(value);
            }
        }
        if (CollectionUtils.isEmpty(excludeNullList)) {
            return null;
        }
        return excludeNullList.toArray(arrayType);
    }

    /**
     * T[] 转 List<T>
     */
    public static <T> List<T> toList(T[] array) {
        return Arrays.asList(array);
    }

    /**
     * T[] 转 List<T> (去除 null & blank)
     */
    public static <T> List<T> toListExcludeNull(T[] array) {
        List<T> list = new ArrayList<>();
        for (T value : array) {
            if (ESObjectUtils.isNotNull(value)) {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * T[] 去除 null & blank
     * arrayType 一般设置为 new T[0]
     */
    public static <T> T[] excludeNull(T[] array, T[] arrayType) {
        List<T> list = new ArrayList<>();
        for (T value : array) {
            if (ESObjectUtils.isNotNull(value)) {
                list.add(value);
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.toArray(arrayType);
    }

    /**
     * List<T> 去除 null & blank
     */
    public static <T> List<T> excludeNull(List<T> list) {
        List<T> excludeList = new ArrayList<>();
        for (T value : list) {
            if (ESObjectUtils.isNotNull(value)) {
                excludeList.add(value);
            }
        }
        return excludeList;
    }
}
