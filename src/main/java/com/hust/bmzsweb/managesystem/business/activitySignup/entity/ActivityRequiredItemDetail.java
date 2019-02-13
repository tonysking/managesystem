package com.hust.bmzsweb.managesystem.business.activitySignup.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//必填信息（用户填写详情）
@Data
@Entity(name="required_item_detail")
public class ActivityRequiredItemDetail {

    @Id
    @GeneratedValue
    private Integer requiredItemDetailId;
    private String name;
    private Integer sex;
    private Integer age;
    private String address;
    private String phone;
    private String wechatNumber;
    private String qqNumber;
    private String email;
    private String school;
    private String grade;
    private String classNumber;
    private String studentId;
    private String workPlace;
    private String department;
    private String position;
    private String jobNumber;
    private String province;
    private String city;
    private String fieldOne;
    private String fieldTwo;
    private String  fieldThree;

}
