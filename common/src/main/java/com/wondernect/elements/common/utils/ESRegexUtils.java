package com.wondernect.elements.common.utils;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2017/8/30.
 * wondernect.com
 *
 * @author sunbeam
 */
public final class ESRegexUtils {

    private static final String EMAIL_REGEX = "[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[0-9a-zA-Z]+";

    private static final String MOBILE_REGEX = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";

    private static final String INTEGER_REGEX = "^[-\\+]?[\\d]*$";

    private static final String DOUBLE_REGEX = "^[-\\+]?[.\\d]*$";

    private static final Map<Integer, String> zoneNum = new HashMap<>();

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "宁夏");
        zoneNum.put(65, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "国外");
    }

    private static final int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; //校验码

    private static final int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2}; //加权因子wi

    /**
     * 验证身份证号有效性
     */
    public static boolean isIdcard(String idCard) {
        //号码长度应为15位或18位
        if (idCard == null || (idCard.length() != 15 && idCard.length() != 18)) {
            return false;
        }
        //校验区位码
        if (!zoneNum.containsKey(Integer.valueOf(idCard.substring(0, 2)))) {
            return false;
        }
        //校验年份
        String year = idCard.length() == 15 ? "19" + idCard.substring(6, 8) : idCard.substring(6, 10);
        final int iyear = Integer.parseInt(year);
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;// 1900年的PASS，超过今年的PASS
        }
        //校验月份
        String month = idCard.length() == 15 ? idCard.substring(8, 10) : idCard.substring(10, 12);
        final int imonth = Integer.parseInt(month);
        if (imonth < 1 || imonth > 12) {
            return false;
        }
        //校验天数
        String day = idCard.length() == 15 ? idCard.substring(10, 12) : idCard.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if (iday < 1 || iday > 31) {
            return false;
        }
        //校验一个合法的年月日
        if (!isValidDate(year + month + day)) {
            return false;
        }
        //校验位数
        int power = 0;
        final char[] cs = idCard.toUpperCase().toCharArray();
        for (int i = 0; i < cs.length; i++) {// 循环比正则表达式更快
            if (i == cs.length - 1 && cs[i] == 'X') {
                break; // 最后一位可以是X或者x
            }
            if (cs[i] < '0' || cs[i] > '9') {
                return false;
            }
            if (i < cs.length - 1) {
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }
        //校验“校验码”
        if (idCard.length() == 15) {
            return true;
        }
        return cs[cs.length - 1] == PARITYBIT[power % 11];
    }

    /**
     * 判断字符串是否为日期格式(合法) 字符串时间 yyyyMMdd
     */
    public static boolean isValidDate(String date) {
        if (date == null) {
            return false;
        }
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        if (date.trim().length() != dataFormat.toPattern().length()) {
            return false;
        }
        dataFormat.setLenient(false); //该方法用于设置Calendar严格解析字符串;默认为true,宽松解析
        try {
            dataFormat.parse(date.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 正则验证
     */
    public static boolean match(String regexPattern, String regexValue) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(regexValue);
        return matcher.matches();
    }

    /**
     * 验证邮箱
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 验证手机号码
     */
    public static boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile(MOBILE_REGEX);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 验证是否为整形(int)
     */
    public static boolean isInteger(String integerString) {
        if (null == integerString || "".equals(integerString)) {
            return false;
        }
        Pattern pattern = Pattern.compile(INTEGER_REGEX);
        Matcher matcher = pattern.matcher(integerString);
        return matcher.matches();
    }

    /**
     * 验证是否浮点型(double或float)
     */
    public static boolean isDouble(String doubleString) {
        if (null == doubleString || "".equals(doubleString)) {
            return false;
        }
        Pattern pattern = Pattern.compile(DOUBLE_REGEX);
        Matcher matcher = pattern.matcher(doubleString);
        return matcher.matches();
    }

    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
        String pattern = "GET:/v5/wfs/fastdfs/user/[^/]+/page";
        String value = "GET:/v5/wfs/fastdfs/user/2shdj_gfaksfhja_shf454524545/page";
        String pattern1 = "GET:/v5/wfs/fastdfs/[^/]+/user/[^/]+/page";
        String value1 = "GET:/v5/wfs/fastdfs/2gh_dgfh/user/dsfj_dsh34/page";
        System.out.println(match(pattern, value));
        System.out.println(match(pattern1, value1));
        System.out.println(isDouble("3"));
        String passwordRegex = "^(?![A-Z]*$)(?![a-z]*$)(?![0-9]*$)(?![^a-zA-Z0-9]*$)\\S+$";
        System.out.println(match(passwordRegex, "1111a"));
    }
}
