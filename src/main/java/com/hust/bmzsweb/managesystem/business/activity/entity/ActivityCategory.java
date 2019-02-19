package com.hust.bmzsweb.managesystem.business.activity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;


//活动类型
@Data
@Entity
public class ActivityCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
}
