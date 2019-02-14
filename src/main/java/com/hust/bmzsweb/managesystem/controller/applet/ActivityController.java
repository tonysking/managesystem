package com.hust.bmzsweb.managesystem.controller.applet;

import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.model.ActivityWithRequiredItemModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  查看活动详情（加入浏览历史）
 *  搜索活动 默认按热度排行
 *  发起活动（系统内部审核报名信息 过滤字符串）
 *  发起者修改活动信息
 *  删除活动
 *  收藏活动
 */
@RestController
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivitySignupService activitySignupService;

    @ApiOperation(value = "创建报名")
    @PostMapping("/activity/creation")
    public JSONResult getActDetailAndSignUpInfo(@RequestBody ActivityWithRequiredItemModel activityInfo){
        Integer actId = activityService.saveActivityInfo(activityInfo);
        return JSONResult.success().add("actId",actId);
    }


    @ApiOperation(value = "查看活动详情附带报名信息")
    @GetMapping("/activity/{actId}/usersignup/{userId}")
    public JSONResult getActDetailAndSignUpInfo(@PathVariable("actId")Integer actId, @PathVariable("userId")Integer userId){
        System.out.println("requset actId:"+actId);
        QueryActivityDetailModel act = activityService.queryAct(actId);
        ActivityRequiredItemDetail detail = activitySignupService.getDetail(userId, actId);
        if(detail!=null)
        {
            return JSONResult.success().add("act",act).add("detail",detail );
        }else{
            return JSONResult.success().add("act",act).add("detail","0" );
        }

    }




}
