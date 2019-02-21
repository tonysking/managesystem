package com.hust.bmzsweb.managesystem.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GSUtil {
    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }
    /**
     * 根据经纬度算距离
     * @param long1
     * @param lat1
     * @param long2
     * @param lat2
     * @return
     */
    public static double getmeter(double long1, double lat1, double long2, double lat2) {
        double a, b, d, sa2, sb2;
        lat1 = rad(lat1);
        lat2 = rad(lat2);
        a = lat1 - lat2;
        b = rad(long1 - long2);

        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2   * EARTH_RADIUS
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        d= d * 1000;
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bg.doubleValue();

    }
    /**
     * 华中科技大学(东校区)
     *
     * 地址：武汉市洪山区珞喻东路1037号
     * 坐标：114.438204,30.517062
     *
     * 光谷国际广场
     *
     * 地址：武汉市洪山区珞喻路889号
     * 坐标：114.404634,30.513423
     *
     * 相距：3244.79m
     */
    public static void main(String[] args) {
        System.out.println(getmeter(114.4382040000, 30.5170620000, 114.4046340000, 30.5134230000));
    }
}
