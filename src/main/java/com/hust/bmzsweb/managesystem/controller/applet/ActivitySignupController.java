package com.hust.bmzsweb.managesystem.controller.applet;


import com.hust.bmzsweb.managesystem.business.activity.model.ActivityWithRequiredItemModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.ActivitySignupModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报名活动
 * 取消报名
 */

@Api(value = "小程序活动报名接口",tags = "小程序活动报名接口")
@RestController()
@RequestMapping("/applet/activitySignUp")
public class ActivitySignupController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivitySignupService activitySignupService;

    @ApiOperation(value = "报名活动")
    @PostMapping("/creation/Sigup")
    public JSONResult saveActivitySignup(@RequestBody SignUpWithRequiredItemDetailModel signUpWithRequiredItemDetailModel)throws Exception{
        Integer actSignupId = activitySignupService.saveActivitySignup(signUpWithRequiredItemDetailModel);
        return JSONResult.success().add("actSignupId",actSignupId);
    }

}
