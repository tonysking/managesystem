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
    private Boolean sex= false;
    private Boolean age= false;
    private Boolean address= false;
    private Boolean phone= false;
    private Boolean wechatNumber= false;
    private Boolean qqNumber= false;
    private Boolean email= false;
    private Boolean school= false;
    private Boolean grade= false;
    private Boolean classNumber= false;
    private Boolean studentId= false;
    private Boolean workPlace= false;
    private Boolean department= false;
    private Boolean position= false;
    private Boolean jobNumber= false;
    private Boolean province= false;
    private Boolean city= false;
    private Boolean fieldOne= false;
    private Boolean fieldTwo= false;
    private Boolean  fieldThree= false;

}
