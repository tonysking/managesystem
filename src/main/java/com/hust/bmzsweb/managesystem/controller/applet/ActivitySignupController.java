package com.hust.bmzsweb.managesystem.controller.applet;


import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.AlterSignUpRequestModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.model.SignUpWithRequiredItemDetailModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        ActivityRequiredItemDetail actSignupId = activitySignupService.saveActivitySignup(signUpWithRequiredItemDetailModel);
        return JSONResult.success().add("actSignupId",actSignupId);
    }

    @ApiOperation(value = "取消报名（置报名状态为1）")
    @GetMapping("/deleteSigup/{userSignId}")
    public JSONResult deleteActivitySignup(@PathVariable("userSignId") Integer userSignId){
        activitySignupService.banActivitySignup(userSignId);
        return JSONResult.success();
    }

    @ApiOperation(value = "取消报名（置报名状态为1）")
    @GetMapping("/deleteSigup")
    public JSONResult deleteActivitySignup(@RequestParam("userId") Integer userId, @RequestParam("actId")Integer actId){
        activitySignupService.banActivitySignup(userId,actId);
        return JSONResult.success();
    }

    @ApiOperation(value = "修改报名信息")
    @PostMapping("/altering/Sigup")
    public JSONResult alterActivitySignup(@RequestBody AlterSignUpRequestModel alterSignUpRequestModel)throws Exception {
        activitySignupService.alterSignUp(alterSignUpRequestModel);
        return JSONResult.success();
    }


}
