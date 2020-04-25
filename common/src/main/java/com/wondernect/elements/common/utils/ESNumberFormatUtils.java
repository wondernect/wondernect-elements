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
        BigDecimal format = new BigDecimal(value);
        return format.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
