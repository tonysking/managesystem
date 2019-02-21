package com.hust.bmzsweb.managesystem.business.activitySignup.model;


import lombok.Data;

import java.util.Date;

@Data
public class RequiredItemModel {
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
