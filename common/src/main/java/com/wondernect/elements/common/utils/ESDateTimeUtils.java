package com.wondernect.elements.common.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created on 2017/9/6.
 * wondernect.com
 * @author sunbeam
 */
public final class ESDateTimeUtils {

    private static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String YEAR_MONTH_DAY_HOUR_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";

    private static final String YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";

    private static final String YEAR_FORMAT = "yyyy";

    private static final String MONTH_FORMAT = "MM";

    private static final String DAY_FORMAT = "dd";

    /**
     * 获取当前时间戳
     */
    public static Long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 根据传入的时间戳计算其当日起始值
     */
    public static Long dayStartTimeMillis(Long timeMillis) {
        return new DateTime(timeMillis).withTimeAtStartOfDay().getMillis();
    }

    /**
     * 根据传入的时间戳计算其当日结束值
     */
    public static Long dayEndTimeMillis(Long timeMillis) {
        return new DateTime(timeMillis).minuteOfDay().withMaximumValue().getMillis();
    }

    /**
     * 获取当天时间起始值
     */
    public static Long currentDayStartTimeMillis() {
        return dayStartTimeMillis(System.currentTimeMillis());
    }

    /**
     * 获取当天时间结束值
     */
    public static Long currentDayEndTimeMillis() {
        return dayEndTimeMillis(System.currentTimeMillis());
    }

    /**
     * 判断传入的时间是否已过去
     */
    public static boolean isPast(Long timeMillis) {
        Long currentDayStartTimeMillis = currentDayStartTimeMillis();
        return timeMillis < currentDayStartTimeMillis;
    }

    /**
     * 判断传入的时间是否是今天
     */
    public static boolean isToday(Long timeMillis) {
        Long currentDayStartTimeMillis = currentDayStartTimeMillis();
        Long currentDayEndTimeMillis = currentDayEndTimeMillis();
        return timeMillis >= currentDayStartTimeMillis && timeMillis <= currentDayEndTimeMillis;
    }

    /**
     * 判断传入的时间是否是未来
     */
    public static boolean isFuture(Long timeMillis) {
        Long currentDayEndTimeMillis = currentDayEndTimeMillis();
        return timeMillis > currentDayEndTimeMillis;
    }

    /**
     * 格式化时间(string date to time millis) date=2018-01-30, formatPattern=yyyy-MM-dd HH:mm:ss
     */
    public static Long formatDate(String date, String formatPattern) {
        Long timestamp;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatPattern);
            DateTime dateTime = DateTime.parse(date, dateTimeFormatter);
            timestamp = dateTime.getMillis();
        } catch (IllegalInstantException e) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatPattern).withZoneUTC();
            DateTime dateTime = DateTime.parse(date, dateTimeFormatter);
            timestamp = dateTime.getMillis();
        }
        return timestamp;
    }

    /**
     * 格式化时间(time millis to string date) timeMillis=1543676387982, formatPattern=yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(Long timeMillis, String formatPattern) {
        String timestamp;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatPattern);
            DateTime dateTime = new DateTime(timeMillis);
            timestamp = dateTime.toString(dateTimeFormatter);
        } catch (IllegalInstantException e) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatPattern).withZoneUTC();
            DateTime dateTime = new DateTime(timeMillis);
            timestamp = dateTime.toString(dateTimeFormatter);
        }
        return timestamp;
    }

    /**
     * 计算两个时间之差
     */
    public static Period interval(Long startTime, Long endTime) {
        Interval interval = new Interval(startTime, endTime);
        return interval.toPeriod();
    }

    /**
     * 计算两个时间之间相差几天
     */
    public static int intervalDays(Long startTime, Long endTime) {
        DateTime startDate = new DateTime(startTime);
        DateTime endDate = new DateTime(endTime);
        return Days.daysBetween(startDate, endDate).getDays();
    }

    /**
     * 计算两个时间之间相差几个工作日
     */
    public static int workDays(Long startTime, Long endTime) {
        Calendar cl1 = Calendar.getInstance();
        Calendar cl2 = Calendar.getInstance();
        cl1.setTime(new Date(startTime));
        cl2.setTime(new Date(endTime));
        int count = 0;
        while (cl1.compareTo(cl2) <= 0) {
            if (cl1.get(Calendar.DAY_OF_WEEK) != 7 && cl1.get(Calendar.DAY_OF_WEEK) != 1)
                count++;
            cl1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return count;
    }

    public static void main(String[] args) {
        String month = formatDate(getCurrentTimestamp(), MONTH_FORMAT);
        System.out.println(Integer.parseInt(month));
        System.out.println(formatDate(1577721600000L, MONTH_FORMAT));
        System.out.println(interval(1557878400000L, 1563192000000L));
        System.out.println(intervalDays(1551058114000L, 1551662914000L));
        System.out.println(workDays(1551058114000L, 1551662914000L));
    }
}
