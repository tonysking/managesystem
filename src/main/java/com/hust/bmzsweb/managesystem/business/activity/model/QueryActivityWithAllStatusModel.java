package com.hust.bmzsweb.managesystem.business.activity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import lombok.Data;

import java.util.Date;

@Data
public class QueryActivityWithAllStatusModel {

    private Integer actId;
    private Integer requiredItemId;
    private String actTitle;
    private String category;
    private String actStatus;
    private String actDetailInfo;
    private String actAddress;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh", timezone="GMT+8")
    private Date actSignupDeadline;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh", timezone="GMT+8")
    private Date actStartTime;
    private Integer actHeat;
    private Integer participantsNumber;
    private Integer actRunStatus;

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

    public QueryActivityWithAllStatusModel(QueryActivityDetailModel activityInfo,Boolean isActivityStart,Boolean isTakePartEnd,Boolean isSponsor,Boolean isAuthorized)
    {
        this.actId = activityInfo.getActId();
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
        this.isDelete = activityInfo.getIsDelete();
        this.isActivityStart = isActivityStart;
        this.isTakePartEnd = isTakePartEnd;
        this.isSponsor = isSponsor;
        this.isAuthorized= isAuthorized;

    }



    public QueryActivityWithAllStatusModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer actHeat, Integer participantsNumber, Integer actRunStatus, Boolean isActivityStart, Boolean isTakePartEnd, Boolean isDelete, Boolean isSponsor, Boolean isAuthorized) {
        this.actId = actId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.category = category;
        this.actStatus = actStatus;
        this.actDetailInfo = actDetailInfo;
        this.actAddress = actAddress;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actHeat = actHeat;
        this.participantsNumber = participantsNumber;
        this.actRunStatus = actRunStatus;
        this.isActivityStart = isActivityStart;
        this.isTakePartEnd = isTakePartEnd;
        this.isDelete = isDelete;
        this.isSponsor = isSponsor;
        this.isAuthorized = isAuthorized;
    }



    public QueryActivityWithAllStatusModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer actHeat, Integer participantsNumber, Integer actRunStatus) {
        this.actId = actId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.category = category;
        this.actStatus = actStatus;
        this.actDetailInfo = actDetailInfo;
        this.actAddress = actAddress;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actHeat = actHeat;
        this.participantsNumber = participantsNumber;
        this.actRunStatus = actRunStatus;
    }

    public QueryActivityWithAllStatusModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer participantsNumber, Integer actRunStatus) {
        this.actId = actId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.category = category;
        this.actStatus = actStatus;
        this.actDetailInfo = actDetailInfo;
        this.actAddress = actAddress;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.participantsNumber = participantsNumber;
        this.actRunStatus = actRunStatus;
    }

    public QueryActivityWithAllStatusModel(Integer actId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer participantsNumber, Integer actRunStatus) {
        this.actId = actId;
        this.actTitle = actTitle;
        this.category = category;
        this.actStatus = actStatus;
        this.actDetailInfo = actDetailInfo;
        this.actAddress = actAddress;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.participantsNumber = participantsNumber;
        this.actRunStatus = actRunStatus;
    }

    public QueryActivityWithAllStatusModel() {
    }
}
