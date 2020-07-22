package com.wondernect.elements.common.utils;

import java.math.BigDecimal;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: ESBigDecimal
 * Author: chenxun
 * Date: 2020-07-21 20:36
 * Description: 浮点类型
 */
public final class ESBigDecimal {

    /**
     * BigDecimal构造
     * @param value 值
     * @return BigDecimal
     */
    public static BigDecimal of(int value) {
        return new BigDecimal(value);
    }

    /**
     * BigDecimal构造
     * @param value 值
     * @return BigDecimal
     */
    public static BigDecimal of(long value) {
        return new BigDecimal(value);
    }

    /**
     * BigDecimal构造
     * @param value 值
     * @return BigDecimal
     */
    public static BigDecimal of(double value) {
        return new BigDecimal(value);
    }

    /**
     * BigDecimal构造
     * @param value 值
     * @return BigDecimal
     */
    public static BigDecimal of(String value) {
        return new BigDecimal(value);
    }

    /**
     * 保留几位小数
     * @param value 数值
     * @param scale 小数位
     * @return 保留后的值
     */
    public static BigDecimal scale(BigDecimal value, int scale) {
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法
     * @param value1 数值1
     * @param value2 数值2
     * @param scale 小数位
     * @return 值
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal value2, int scale) {
        return value1.divide(value2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法
     * @param value1 数值1
     * @param value2 数值2
     * @param scale 小数位
     * @return 值
     */
    public static BigDecimal multiply(BigDecimal value1, BigDecimal value2, int scale) {
        return value1.multiply(value2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
}
