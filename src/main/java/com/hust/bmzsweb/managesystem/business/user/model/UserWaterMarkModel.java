package com.hust.bmzsweb.managesystem.business.user.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserWaterMarkModel {
    private Date timestamp;
    private String appid;
}
