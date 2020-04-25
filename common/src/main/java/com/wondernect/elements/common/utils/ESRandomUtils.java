package com.wondernect.elements.common.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created on 2017/8/30.
 * wondernect.com
 * @author sunbeam
 */
public final class ESRandomUtils {

    private static final char[] randomChars = {
            'A','B','D','E','F','G','H','M','N','Q','R','T','Y',
            'a','b','d','e','f','g','h','m','n','q','r','t','y',
            '1','2','3','4','5','6','7','8','9'};

    /**
     * 生成指定长度随机数字
     */
    public static String randomNumberString(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    /**
     * 生成指定长度指定字符范围字符
     */
    public static String randomStringWithChars(int length) {
        return RandomStringUtils.random(length, randomChars);
    }
}
