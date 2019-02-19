package com.hust.bmzsweb.managesystem.business.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//微信授权登录用户
@Data
@Entity(name="wxapp_user")
public class WXUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickName;
    private String province;
    private String city;
    private String openId;
    private String avatarUrl;
}
