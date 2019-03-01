package com.hust.bmzsweb.managesystem.business.activity.model;

import lombok.Data;

@Data
public class QueryActivityLocationListModel {

    private Integer actId;
    private String  actTitle;
    private String category;
    private String  actAddress;
    private Double longitude;
    private Double latitude;
    private Integer participantsNumber;
    private Integer actHeat;

    //用于前端判断活动是否报名截止(被禁止或失效)来调整显示样式
    private Integer actStatus; //(暂不用)
    private Integer actRunStatus;//(暂不用
    private Boolean isSignupEnd;


    public QueryActivityLocationListModel() {
    }

    public QueryActivityLocationListModel(Integer actId, String actTitle, String category, String actAddress, Double longitude, Double latitude, Integer participantsNumber, Integer actHeat) {
        this.actId = actId;
        this.actTitle = actTitle;
        this.category = category;
        this.actAddress = actAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.participantsNumber = participantsNumber;
        this.actHeat = actHeat;
    }
}

