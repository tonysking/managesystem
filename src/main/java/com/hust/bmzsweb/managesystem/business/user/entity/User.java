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

    @Column(length = 64)
    private String userNickName;

    @Column(length = 64)
    private String userPhone;

    @Column(length = 64)
    private String userAddress;

    @Column(nullable = false)
    private Integer userStatus;

    @Column(nullable = false)
    private Integer userRole;


    public User(){

    }

    public User(String userNickName)
    {
        this.userNickName = userNickName;
    }

}
