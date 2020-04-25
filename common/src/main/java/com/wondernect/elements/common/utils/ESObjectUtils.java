package com.wondernect.elements.common.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * wondernect.com
 * Created with common
 * Created By chenxun
 * Date: 2018/3/29
 * Time: 17:59
 */
public class ESObjectUtils extends ObjectUtils {

    /**
     * 对象非null判断
     */
    public static boolean isNotNull(Object object) {
        boolean notNone = allNotNull(object);
        if (notNone && object instanceof String) {
            String value = (String) object;
            notNone = StringUtils.isNotBlank(value);
        }
        return notNone;
    }

    /**
     * 对象为null判断
     */
    public static boolean isNull(Object object) {
        return !isNotNull(object);
    }
}
