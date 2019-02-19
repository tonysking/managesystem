package com.hust.bmzsweb.managesystem.business.userCollection;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.hust.bmzsweb.managesystem.business.userCollection.UserCollectionEntity;
import java.util.Date;

@Data
public class UserCollectionModel {

    private Integer actId;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh", timezone="GMT+8")
    private Date collectTime;
    private Integer userId;

    public UserCollectionModel(Integer actId,  Date collectTime,  Integer userId) {
        this.actId = actId;
        this.collectTime = collectTime;
        this.userId = userId;
    }
    public  UserCollectionEntity createCollection(){
        return new UserCollectionEntity(collectTime,actId,userId);
    }
}
