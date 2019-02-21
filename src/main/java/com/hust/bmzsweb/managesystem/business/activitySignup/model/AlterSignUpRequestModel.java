package com.hust.bmzsweb.managesystem.business.activitySignup.model;

import lombok.Data;

@Data
public class AlterSignUpRequestModel {
     private Integer userSignId;

    private RequiredItemDetailModel requiredItemDetailModel;

    public void check(){


    }

}
