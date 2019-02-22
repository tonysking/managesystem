package com.hust.bmzsweb.managesystem.business.activity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//活动信息
@Entity
@Data
public class ActivityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actId;
    private Integer userId;
    private Integer requiredItemId;
    private String actTitle;
    private String actDetailInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date actSignupDeadline;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date actStartTime;
    private Boolean actReminder;
    private Integer categoryType;
    private String actAddress;
    private Double longitude;
    private Double latitude;
    private Integer participantsNumber;
    private Integer actHeat;
    private Integer actLike;
    private Integer actStatus;
    private Integer actRunStatus;
    private Date createTime;
    private Date updateTime;




    private Boolean isLimitNum;
    private Integer maxNum;
    private Boolean isPrivate;
    private String actPassword;

    private Boolean isDelete;

    public ActivityInfo(Integer userId, Integer requiredItemId, String actTitle, String actDetailInfo, Date actSignupDeadline, Date actStartTime, Boolean actReminder, Integer categoryType, String actAddress, Double longitude, Double latitude, Integer participantsNumber, Integer actHeat, Integer actLike, Integer actStatus, Integer actRunStatus, Date createTime, Date updateTime, Boolean isLimitNum, Integer maxNum, Boolean isPrivate, String actPassword, Boolean isDelete) {
        this.userId = userId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.actDetailInfo = actDetailInfo;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actReminder = actReminder;
        this.categoryType = categoryType;
        this.actAddress = actAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.participantsNumber = participantsNumber;
        this.actHeat = actHeat;
        this.actLike = actLike;
        this.actStatus = actStatus;
        this.actRunStatus = actRunStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isLimitNum = isLimitNum;
        this.maxNum = maxNum;
        this.isPrivate = isPrivate;
        this.actPassword = actPassword;
        this.isDelete = isDelete;
    }

    public ActivityInfo(Integer userId, Integer requiredItemId, String actTitle, String actDetailInfo, Date actSignupDeadline, Date actStartTime, Boolean actReminder, Integer categoryType, String actAddress, Double longitude, Double latitude, Integer participantsNumber, Integer actStatus, Integer actRunStatus, Date createTime, Date updateTime, Boolean isLimitNum, Integer maxNum, Boolean isPrivate, String actPassword, Boolean isDelete) {
        this.userId = userId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.actDetailInfo = actDetailInfo;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actReminder = actReminder;
        this.categoryType = categoryType;
        this.actAddress = actAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.participantsNumber = participantsNumber;
        this.actStatus = actStatus;
        this.actRunStatus = actRunStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isLimitNum = isLimitNum;
        this.maxNum = maxNum;
        this.isPrivate = isPrivate;
        this.actPassword = actPassword;
        this.isDelete = isDelete;
    }
    public ActivityInfo(Integer userId, Integer requiredItemId, String actTitle, String actDetailInfo, Date actSignupDeadline, Date actStartTime, Boolean actReminder, Integer categoryType, String actAddress, Double longitude, Double latitude, Integer participantsNumber, Integer actHeat, Integer actLike, Integer actStatus, Integer actRunStatus, Date createTime, Date updateTime, Boolean isLimitNum, Integer maxNum, Boolean isPrivate, String actPassword) {
        this.userId = userId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.actDetailInfo = actDetailInfo;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actReminder = actReminder;
        this.categoryType = categoryType;
        this.actAddress = actAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.participantsNumber = participantsNumber;
        this.actHeat = actHeat;
        this.actLike = actLike;
        this.actStatus = actStatus;
        this.actRunStatus = actRunStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isLimitNum = isLimitNum;
        this.maxNum = maxNum;
        this.isPrivate = isPrivate;
        this.actPassword = actPassword;
    }

    public ActivityInfo(Integer userId, Integer requiredItemId, String actTitle, String actDetailInfo, Date actSignupDeadline, Date actStartTime, Boolean actReminder, Integer categoryType, String actAddress, Double longitude, Double latitude, Integer participantsNumber, Integer actHeat, Integer actLike, Integer actStatus, Integer actRunStatus, Date createTime, Date updateTime, Boolean isDelete, Boolean isLimitNum, Integer maxNum, Boolean isPrivate, String actPassword) {
        this.userId = userId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.actDetailInfo = actDetailInfo;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actReminder = actReminder;
        this.categoryType = categoryType;
        this.actAddress = actAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.participantsNumber = participantsNumber;
        this.actHeat = actHeat;
        this.actLike = actLike;
        this.actStatus = actStatus;
        this.actRunStatus = actRunStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.isLimitNum = isLimitNum;
        this.maxNum = maxNum;
        this.isPrivate = isPrivate;
        this.actPassword = actPassword;
    }

    public ActivityInfo() {
    }

    public ActivityInfo(Integer userId, Integer requiredItemId, String actTitle, String actDetailInfo, Date actSignupDeadline, Date actStartTime, Boolean actReminder, Integer categoryType, String actAddress, Double longitude, Double latitude, Integer participantsNumber, Integer actHeat, Integer actLike, Integer actStatus, Integer actRunStatus, Date createTime, Date updateTime) {
        this.userId = userId;
        this.requiredItemId = requiredItemId;
        this.actTitle = actTitle;
        this.actDetailInfo = actDetailInfo;
        this.actSignupDeadline = actSignupDeadline;
        this.actStartTime = actStartTime;
        this.actReminder = actReminder;
        this.categoryType = categoryType;
        this.actAddress = actAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.participantsNumber = participantsNumber;
        this.actHeat = actHeat;
        this.actLike = actLike;
        this.actStatus = actStatus;
        this.actRunStatus = actRunStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
