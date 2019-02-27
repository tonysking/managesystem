package com.hust.bmzsweb.managesystem.business.activitySignup.model;

import lombok.Data;

@Data
public class AlterSignUpRequestModel {
//     private Integer userSignId;
    private Integer actId;
    private RequiredItemDetailModel requiredItemDetailModel;
    private Integer userId;

    public void check(){


    }

}
