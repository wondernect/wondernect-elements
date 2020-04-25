package com.wondernect.elements.common.utils;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ESDistanceUtils
 * Author: chenxun
 * Date: 2019/5/19 17:04
 * Description:
 */
public final class ESDistanceUtils {

    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param startLongitude 起点经度
     * @param startLatitude 起点纬度
     * @param targetLongitude 终点经度
     * @param targetLatitude 终点纬度
     * @return 返回距离 单位：米
     */
    public static double distance(double startLongitude, double startLatitude, double targetLongitude, double targetLatitude) {
        double a, b, R;
        R = 6378137; // 地球半径
        startLatitude = startLatitude * Math.PI / 180.0;
        targetLatitude = targetLatitude * Math.PI / 180.0;
        a = startLatitude - targetLatitude;
        b = (startLongitude - targetLongitude) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(startLatitude) * Math.cos(targetLatitude) * sb2 * sb2));
        Double kilo = d / 1000;
        return ESNumberFormatUtils.formatDouble(kilo, 2);
    }

    public static void main(String[] args) {
        String startPlace = "厦门高崎国际机场";
        Double startLon = 118.143639;
        Double startLat = 24.545038;
        String targetPlace = "厦门北站";
        Double targetLon = 118.080553;
        Double targetLat = 24.642666;
        System.out.println(distance(startLon, startLat, targetLon, targetLat) + "公里");
    }
}
