package com.wondernect.elements.common.utils;

import java.math.BigDecimal;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: NumberFormatUtils
 * Author: chenxun
 * Date: 2018/9/11 上午10:34
 * Description: 数字格式化工具
 */
public final class ESNumberFormatUtils {

    /**
     * 格式化double数字，保留scale位小数
     * @param value 数字
     * @param scale 保留小数位数
     * @return 格式化后数字
     */
    public static Double formatDouble(double value, int scale) {
        // new BigDecimal(0)有可bai能得到的是0.000000000000001之类的值，这是Java的bug。
        // 初始化0或者其他基础类型数字转换为BigDecimal最好用字符串，比如初始化为0，用new BigDecimal("0")
        if (value == 0d) {
            return 0d;
        }
        BigDecimal format = new BigDecimal(value);
        return format.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留几位小数
     * @param value 数值
     * @param scale 小数位
     * @return 保留后的值
     */
    public static Double formatDouble(BigDecimal value, int scale) {
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法
     * @param value1 数值1
     * @param value2 数值2
     * @param scale 小数位
     * @return 值
     */
    public static Double divide(double value1, double value2, int scale) {
        return new BigDecimal(value1).divide(new BigDecimal(value2), scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 乘法
     * @param value1 数值1
     * @param value2 数值2
     * @param scale 小数位
     * @return 值
     */
    public static Double multiply(double value1, double value2, int scale) {
        return new BigDecimal(value1).multiply(new BigDecimal(value2)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(ESBigDecimal.of("4.3").divideToIntegralValue(ESBigDecimal.of("2.1")).intValue());
        System.out.println(ESBigDecimal.of("4.3").remainder(ESBigDecimal.of("2.1")).doubleValue());
        BigDecimal[] bigDecimals = ESBigDecimal.of("4.3").divideAndRemainder(ESBigDecimal.of("2.1"));
        for (BigDecimal bigDecimal : bigDecimals) {
            System.out.println(bigDecimal.doubleValue());
        }
        System.out.println(ESBigDecimal.divide(ESBigDecimal.of(4.3), ESBigDecimal.of(2.1), 4));
    }
}
