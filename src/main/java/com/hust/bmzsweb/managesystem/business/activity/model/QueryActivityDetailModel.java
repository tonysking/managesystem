package com.hust.bmzsweb.managesystem.business.activity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class QueryActivityDetailModel {


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
    private Boolean isDelete;
    private Integer participantsNumber;
    private Integer actRunStatus;

    private Boolean isLimitNum;
    private Integer maxNum;
    private Boolean isPrivate;
    private String actPassword;

    public QueryActivityDetailModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer actHeat, Boolean isDelete, Integer participantsNumber, Integer actRunStatus, Boolean isLimitNum, Integer maxNum, Boolean isPrivate, String actPassword) {
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
        this.isDelete = isDelete;
        this.participantsNumber = participantsNumber;
        this.actRunStatus = actRunStatus;
        this.isLimitNum = isLimitNum;
        this.maxNum = maxNum;
        this.isPrivate = isPrivate;
        this.actPassword = actPassword;
    }

    public QueryActivityDetailModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer actHeat, Boolean isDelete, Integer participantsNumber, Integer actRunStatus) {
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
        this.isDelete = isDelete;
        this.participantsNumber = participantsNumber;
        this.actRunStatus = actRunStatus;
    }

    public QueryActivityDetailModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer actHeat, Integer participantsNumber, Integer actRunStatus) {
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

    public QueryActivityDetailModel(Integer actId, Integer requiredItemId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer participantsNumber, Integer actRunStatus) {
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

    public QueryActivityDetailModel(Integer actId, String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer participantsNumber, Integer actRunStatus) {
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

    public QueryActivityDetailModel() {
    }
}
