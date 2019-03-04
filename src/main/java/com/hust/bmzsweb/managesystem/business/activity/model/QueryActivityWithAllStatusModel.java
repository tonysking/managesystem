package com.hust.bmzsweb.managesystem.business.activity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import lombok.Data;

import java.util.Date;

@Data
public class QueryActivityWithAllStatusModel {

    private Integer actId;
    private Integer userId;
    private Integer requiredItemId;
    private String actTitle;
    private String category;
    private String actStatus;
    private String actDetailInfo;
    private String actAddress;
    private Double longitude;
    private Double latitude;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh", timezone="GMT+8")
    private Date actSignupDeadline;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh", timezone="GMT+8")
    private Date actStartTime;
    private Integer actHeat;
    private Integer participantsNumber;
    private Integer actRunStatus;

    //种类索引
    private Integer type;

    private Boolean isLimitNum;
    private Integer maxNum;
    private Boolean isPrivate;
    private String actPassword;

    //活动的各种状态属性

    //活动是否开始
    private Boolean isActivityStart;
    //报名是否结束
    private Boolean isTakePartEnd;
    //活动是否被删除
    private Boolean isDelete;
    //是否为发起者
    private Boolean isSponsor;
    //是否审核通过
    private Boolean isAuthorized;

    public QueryActivityWithAllStatusModel(QueryActivityDetailModel activityInfo,Boolean isActivityStart,Boolean isTakePartEnd,Boolean isSponsor,Boolean    isAuthorized,Integer type,Double latitude,Double longitude)
    {
        this.actId = activityInfo.getActId();
        this.userId = activityInfo.getUserId();
        this.requiredItemId = activityInfo.getRequiredItemId();
        this.actTitle = activityInfo.getActTitle();
        this.category = activityInfo.getCategory();
        this.actStatus = activityInfo.getActStatus();
        this.actDetailInfo = activityInfo.getActDetailInfo();
        this.actAddress = activityInfo.getActAddress();
        this.actSignupDeadline = activityInfo.getActSignupDeadline();
        this.actStartTime = activityInfo.getActStartTime();
        this.actHeat = activityInfo.getActHeat();
        this.participantsNumber = activityInfo.getParticipantsNumber();
        this.actRunStatus = activityInfo.getActRunStatus();

        this.isLimitNum = activityInfo.getIsLimitNum();
        this.maxNum = activityInfo.getMaxNum();
        this.isPrivate = activityInfo.getIsPrivate();
        this.actPassword = activityInfo.getActPassword();
        this.isDelete = activityInfo.getIsDelete();
        this.isActivityStart = isActivityStart;
        this.isTakePartEnd = isTakePartEnd;
        this.isSponsor = isSponsor;
        this.isAuthorized= isAuthorized;

        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;

    }


    public QueryActivityWithAllStatusModel() {
    }
}
