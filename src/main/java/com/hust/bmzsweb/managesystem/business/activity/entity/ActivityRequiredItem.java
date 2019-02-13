package com.hust.bmzsweb.managesystem.business.activity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//必填信息
@Data
@Entity
public class ActivityRequiredItem {

    @Id
    @GeneratedValue
    private Integer requiredItemId;
    private Integer name;
    private Integer sex;
    private Integer age;
    private Integer address;
    private Integer phone;
    private Integer wechatNumber;
    private Integer qqNumber;
    private Integer email;
    private Integer school;
    private Integer grade;
    private Integer classNumber;
    private Integer studentId;
    private Integer workPlace;
    private Integer department;
    private Integer position;
    private Integer jobNumber;
    private Integer province;
    private Integer city;
    private Integer fieldOne;
    private Integer fieldTwo;
    private Integer  fieldThree;

}
