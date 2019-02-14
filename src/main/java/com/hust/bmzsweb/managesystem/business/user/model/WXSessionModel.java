package com.hust.bmzsweb.managesystem.business.user.model;

import lombok.Data;

@Data
public class WXSessionModel {

    private String openid;
    private String session_key;

}
