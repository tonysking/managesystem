package com.hust.bmzsweb.managesystem.business.userCollection;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//用户收藏
@Entity(name = "user_collection")
@Data
public class UserCollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;
    private Date collectTime;
    private Integer actId;
    private Integer userId;
    public UserCollectionEntity(Date collectTime,Integer actId,Integer userId) {
        this.collectTime = collectTime;
        this.actId = actId;
        this.userId = userId;
    }

}
