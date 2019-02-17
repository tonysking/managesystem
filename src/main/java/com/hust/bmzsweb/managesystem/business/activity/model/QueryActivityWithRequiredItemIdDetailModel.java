package com.hust.bmzsweb.managesystem.business.activity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class QueryActivityWithRequiredItemIdDetailModel {


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

    private Integer participantsNumber;
    private Integer actRunStatus;

    public QueryActivityWithRequiredItemIdDetailModel(Integer actId, Integer requiredItemId,String actTitle, String category, String actStatus, String actDetailInfo, String actAddress, Date actSignupDeadline, Date actStartTime, Integer participantsNumber, Integer actRunStatus) {
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

    public QueryActivityWithRequiredItemIdDetailModel() {
    }
}
