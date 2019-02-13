package com.hust.bmzsweb.managesystem.controller.web;

import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import com.hust.bmzsweb.managesystem.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.layout.Background;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *   查看/搜索活动列表
 *    查看活动详情
 *    管理员禁止活动
 *    管理员允许活动
 * */
@Api(value = "活动接口",tags = "活动接口")
@RestController
@RequestMapping("/admin")
public class AdminActivityController extends BaseController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivitySignupService activitySignupService;

    @ApiOperation(value = "显示活动信息主页")
    @RequestMapping({"/activity/","/activity/index"})
    public ModelAndView index() {
        return new ModelAndView("admin/activity/index");
    }


    @ApiOperation(value = "模糊查询所有活动")
    @GetMapping("/activity/list")
    public JSONResult queryActivities(@RequestParam(value="searchText",required=false) String searchText, Integer type){
        Page<QueryActivityListModel> page = activityService.findAllActsByActTitleContaining(searchText, getPageRequest());
        return JSONResult.success().add("page", page);
    }




    @ApiOperation(value = "查看活动详情")
    @GetMapping("/activity/{actId}")
    public JSONResult getActDetail(@PathVariable("actId")Integer actId){
        QueryActivityDetailModel act = activityService.queryAct(actId);
        return JSONResult.success().add("act",act);
    }

    @ApiOperation(value = "查看活动详情附带报名信息")
    @GetMapping("/activity/{actId}/usersignup/{userId}")
    public JSONResult getActDetailAndSignUpInfo(@PathVariable("actId")Integer actId, @PathVariable("userId")Integer userId){
        System.out.println("requset userId:"+userId);
        QueryActivityDetailModel act = activityService.queryAct(actId);
        ActivityRequiredItemDetail detail = activitySignupService.getDetail(userId, actId);
        return JSONResult.success().add("act",act).add("detail",detail );
    }


    @ApiOperation(value = "禁止活动")
    @GetMapping("/activity/{actId}/forbiddance")
    public JSONResult forbidActivity(@PathVariable("actId")Integer actId)
    {
        activityService.banActivity(actId);
        return JSONResult.success();
    }

    @ApiOperation("设置活动状态")
    @PostMapping("/activity/{actId}/actRunStatus")
    public JSONResult allowActivity(@PathVariable("actId")Integer actId,Integer actRunStatus) {
        activityService.updateActRunstatus(actId,actRunStatus );
        return JSONResult.success();
    }
    @ApiOperation(value = "允许活动")
    @GetMapping("/activity/{actId}/allowance")
    public JSONResult allowActivity(@PathVariable("actId")Integer actId){
        activityService.allowActivity(actId);
        return JSONResult.success();
    }



}
