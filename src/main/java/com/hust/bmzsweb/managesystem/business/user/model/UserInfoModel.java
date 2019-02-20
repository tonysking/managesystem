package com.hust.bmzsweb.managesystem.business.user.model;

import lombok.Data;

@Data
public class UserInfoModel {
    private String openId;
    private String nickName;
    private Integer gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private UserWaterMarkModel watermark;

}
