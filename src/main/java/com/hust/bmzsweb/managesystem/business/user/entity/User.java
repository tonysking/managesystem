package com.hust.bmzsweb.managesystem.business.user.entity;
import lombok.Data;

import javax.persistence.*;

//用户
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userOpenid;


    private String userNickName;


    private String userPhone;


    private String userAddress;

    @Column(nullable = false)
    private Integer userStatus;

    @Column(nullable = false)
    private Integer userRole;


    public User(){

    }

    public User(String userOpenid, String userNickName) {
        this.userOpenid = userOpenid;
        this.userNickName = userNickName;
    }

    public User(String userNickName)
    {
        this.userNickName = userNickName;
    }

}
