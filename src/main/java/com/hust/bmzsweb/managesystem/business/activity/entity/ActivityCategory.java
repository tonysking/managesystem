package com.hust.bmzsweb.managesystem.business.activity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

//活动类型
@Data
@Entity
public class ActivityCategory {

    @Id
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
}
