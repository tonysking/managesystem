package com.hust.bmzsweb.managesystem.business.activity.enums;

import lombok.Getter;

@Getter
public enum  ActStatusEnum {

    INREVIEW(0,"审核中"),
    ALLOW(1,"审核通过"),
    FORBID(2,"审核未通过")
    ;


    private Integer actStatusId;
    private String actStatusName;

    ActStatusEnum(Integer actStatusId, String actStatusName) {
        this.actStatusId = actStatusId;
        this.actStatusName = actStatusName;
    }
}
