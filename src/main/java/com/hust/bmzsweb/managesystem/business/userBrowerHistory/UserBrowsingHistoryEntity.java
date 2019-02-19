package com.hust.bmzsweb.managesystem.business.userBrowerHistory;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//用户浏览历史
@Entity(name = "user_browsing_history")
@Data
public class UserBrowsingHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hId;
    private Date browsingTime;
    private Integer actId;
    private Integer userId;
    public UserBrowsingHistoryEntity( Integer actId, Integer userId) {
        this.actId = actId;
        this.userId = userId;
    }
}
