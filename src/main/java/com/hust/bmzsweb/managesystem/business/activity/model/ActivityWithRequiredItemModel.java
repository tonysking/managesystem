package com.hust.bmzsweb.managesystem.business.activity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ActivityWithRequiredItemModel {
    @Id
    @GeneratedValue
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
    private Integer actStatus;
    private Integer actRunStatus;
    private Date createTime;
    private Date updateTime;
    private Boolean isDelete;

    private Boolean isLimitNum;
    private Integer maxNum;
    private Boolean isPrivate;
    private String actPassword;

    private ActivityRequiredItem activityRequiredItem;

    public  ActivityInfo createAct(){
        return new ActivityInfo(userId,requiredItemId,actTitle,actDetailInfo,actSignupDeadline,
                actStartTime,actReminder,categoryType,actAddress,longitude,latitude,
                participantsNumber,actStatus,actRunStatus,createTime,updateTime,isLimitNum,maxNum,isPrivate,actPassword,isDelete);
    }

    public  ActivityInfo createActWithActHeatActLikeZero(){
        return new ActivityInfo(userId,actId,requiredItemId,actTitle,actDetailInfo,actSignupDeadline,
                actStartTime,actReminder,categoryType,actAddress,longitude,latitude,
                participantsNumber,0,0,actStatus,actRunStatus,createTime,updateTime,isLimitNum,maxNum,isPrivate,actPassword,isDelete);
    }
}
