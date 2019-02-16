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
public class UserBrowserHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer h_id;
    private Date browsing_time;
    private Integer act_id;
    private Integer user_id;

    public UserBrowserHistoryEntity(  Integer act_id, Integer user_id) {
        this.act_id = act_id;
        this.user_id = user_id;
    }
}
