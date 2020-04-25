package com.wondernect.elements.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * wondernect.com
 * Created with common
 * Created By chenxun
 * Date: 2018/3/29
 * Time: 17:40
 */
public class ESStringUtils extends StringUtils {

    /**
     * 去除字符串两端blank后进行为空判断
     */
    public static boolean isRealEmpty(String value) {
        return isBlank(value);
    }

    /**
     * 去除字符串两端blank后进行非空判断
     */
    public static boolean isNotRealEmpty(String value) {
        return isNotBlank(value);
    }

    /**
     * 截取从index到指定char位置的字符串
     */
    public static String subStringValueFromIndexToCharPlace(String value, int fromIndex, String toChar) {
        if (isNotRealEmpty(value)) {
            int charPlace = value.lastIndexOf(toChar);
            if ((charPlace > -1) && (charPlace < value.length())) {
                return value.substring(fromIndex, charPlace);
            }
        }
        return value;
    }

    /**
     * 截取从指定char位置到index的字符串
     */
    public static String subStringValueFromCharPlaceToIndex(String value, String fromChar, int toIndex) {
        if (isNotRealEmpty(value)) {
            int charPlace = value.indexOf(fromChar);
            if ((charPlace > -1) && (charPlace < value.length())) {
                return value.substring(charPlace, toIndex);
            }
        }
        return value;
    }

    /**
     * 截取从指定char位置到指定char位置的字符串
     */
    public static String subStringValueFromCharPlaceToCharPlace(String value, String fromChar, String toChar) {
        if (isNotRealEmpty(value)) {
            int fromCharPlace = value.indexOf(fromChar);
            int toCharPlace = value.lastIndexOf(toChar);
            if ((fromCharPlace > -1) && (fromCharPlace < value.length()) && (toCharPlace > -1) && (toCharPlace < value.length()) && (fromCharPlace < toCharPlace)) {
                return value.substring(fromCharPlace, toCharPlace);
            }
        }
        return value;
    }

    /**
     * 在指定字符位置插入指定字符串
     */
    public static String insertStringValueIntoCharPlace(String original, String value, String indexChar, int offset) {
        if (isNotRealEmpty(original) && isNotRealEmpty(value) && isNotRealEmpty(indexChar)) {
            StringBuilder stringBuilder = new StringBuilder(original);
            int index = original.indexOf(indexChar);
            stringBuilder.insert(index + offset, value);
            return stringBuilder.toString();
        }
        return original;
    }

    /**
     * 字符 ISO 转 UTF_8
     */

    public static String transformISOToUTF_8(String value){
        if (StringUtils.isBlank(value)){
            return "";
        }
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println("/v5/wfs/fastdfs/{user_id}/user/{user_id}/page".replaceAll("[{][^/]*[}]", "[^/]+"));
        String value = "127345674574|333";
        System.out.println(ESStringUtils.subStringValueFromIndexToCharPlace(value, 0, "|"));
        String str1 = "学习《新时代“习近平”精神》";
        String str2 = "学习《新时代\"习近平\"精神》";
        System.out.println(ESStringUtils.replaceEach(str1, new String[]{"\"","“", "”", "《", "》"}, new String[]{"1","2","3","4","5"}));
        System.out.println(ESStringUtils.replaceEach(str2, new String[]{"\"","“", "”", "《", "》"}, new String[]{"1","2","3","4","5"}));
    }
}
