package com.hust.bmzsweb.managesystem.business.activitySignup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import lombok.Data;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class SignUpWithRequiredItemDetailModel {
       private Integer userId;
    private Integer actId;

    private RequiredItemDetailModel requiredItemDetailModel;

    public void check(){


    }

}
