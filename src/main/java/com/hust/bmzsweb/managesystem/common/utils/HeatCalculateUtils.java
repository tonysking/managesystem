package com.hust.bmzsweb.managesystem.common.utils;

import com.hust.bmzsweb.managesystem.common.enums.HeatEnum;

//新热度=原热度*系数+参数
public class HeatCalculateUtils {

    public static int addHeat(int preHeat, HeatEnum heatEnum){
        return (int)(preHeat*heatEnum.getPropotion()+heatEnum.getCount());
    }

    public static int reduceHeat(int preHeat, HeatEnum heatEnum){
        return (int)(preHeat*(2-(int)heatEnum.getPropotion())-heatEnum.getCount());
    }
}
