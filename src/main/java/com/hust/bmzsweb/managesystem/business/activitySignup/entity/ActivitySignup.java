package com.hust.bmzsweb.managesystem.business.activitySignup.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//用户报名信息
@Data
@Entity(name="user_signup_info")
public class ActivitySignup {

    @Id
    @GeneratedValue
    private Integer userSignupId;
    private Integer userId;
    private Integer actId;
    private Integer userSignupStatus;
    private Integer requiredItemDetailId;
    private Date createTime;
    private Date updateTime;


}
