package com.hust.bmzsweb.managesystem.business.activity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//活动信息
@Entity
@Data
public class ActivityInfo {

    @Id
    @GeneratedValue
    private Integer actId;
    private Integer userId;
    private Integer requiredItemId;
    private String actTitle;
    private String actDetailInfo;
    private Date actSignupDeadline;
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

    private Boolean isLimitnum;
    private Integer maxNum;
    private Boolean isPrivate;
    private String actPassword;

}
