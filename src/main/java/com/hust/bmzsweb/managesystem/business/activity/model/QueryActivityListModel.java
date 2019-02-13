package com.hust.bmzsweb.managesystem.business.activity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
public class QueryActivityListModel {

    private Integer actId;
    private String actTitle;
    private String category;
    private String actStatus;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a",locale="zh", timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a",locale="zh", timezone="GMT+8")
    private Date updateTime;

    private Integer actRunStatus;

    public QueryActivityListModel() {
    }

    public QueryActivityListModel(Integer actId, String actTitle, String category, String actStatus, Date createTime, Date updateTime) {
        this.actId = actId;
        this.actTitle = actTitle;
        this.category = category;
        this.actStatus = actStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public QueryActivityListModel(Integer actId, String actTitle, String category, String actStatus, Date createTime, Date updateTime, Integer actRunStatus) {
        this.actId = actId;
        this.actTitle = actTitle;
        this.category = category;
        this.actStatus = actStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.actRunStatus = actRunStatus;
    }
}
