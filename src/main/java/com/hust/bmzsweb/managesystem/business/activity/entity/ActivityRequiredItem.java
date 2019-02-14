package com.hust.bmzsweb.managesystem.business.activity.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//必填信息
@Data
@Entity(name="required_item")
public class ActivityRequiredItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requiredItemId;
    private Boolean name;
    private Boolean sex;
    private Boolean age;
    private Boolean address;
    private Boolean phone;
    private Boolean wechatNumber;
    private Boolean qqNumber;
    private Boolean email;
    private Boolean school;
    private Boolean grade;
    private Boolean classNumber;
    private Boolean studentId;
    private Boolean workPlace;
    private Boolean department;
    private Boolean position;
    private Boolean jobNumber;
    private Boolean province;
    private Boolean city;
    private Boolean fieldOne;
    private Boolean fieldTwo;
    private Boolean  fieldThree;

}
