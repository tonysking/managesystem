package com.hust.bmzsweb.managesystem.business.activitySignup.model;


import lombok.Data;

import java.util.Date;

@Data
public class ActivitySignupModel {

    private Integer userId;
    private Integer actId;

    public ActivitySignupModel(Integer actId, Date collectTime, Integer userId) {
        this.actId = actId;
        this.userId = userId;
    }
}
