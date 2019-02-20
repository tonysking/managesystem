package com.hust.bmzsweb.managesystem.controller.applet;

import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityCategory;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityInfo;
import com.hust.bmzsweb.managesystem.business.activity.entity.ActivityRequiredItem;
import com.hust.bmzsweb.managesystem.business.activity.model.ActivityWithRequiredItemModel;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityDetailModel;
import com.hust.bmzsweb.managesystem.business.activitySignup.ActivitySignupService;
import com.hust.bmzsweb.managesystem.business.activitySignup.entity.ActivityRequiredItemDetail;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  查看活动详情（加入浏览历史）
 *  搜索活动 默认按热度排行
 *  发起活动（系统内部审核报名信息 过滤字符串）
 *  发起者修改活动信息
 *  删除活动
 *  收藏活动
 */
@Api(value = "小程序活动接口",tags = "小程序活动接口")
@RestController()
@RequestMapping("/applet/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    ActivitySignupService activitySignupService;

    @ApiOperation(value = "创建报名")
    @PostMapping("/creation")
    public JSONResult getActDetailAndSignUpInfo(@RequestBody ActivityWithRequiredItemModel activityInfo){
        System.out.println("input activityInfo:"+activityInfo);
        Integer actId = activityService.saveActivityInfo(activityInfo);
        return JSONResult.success().add("actId",actId);
    }


    @ApiOperation(value = "查看活动详情附带报名信息")
    @GetMapping("/{actId}/usersignup/{userId}")
    public JSONResult getActDetailAndSignUpInfo(@PathVariable("actId")Integer actId, @PathVariable("userId")Integer userId){
        System.out.println("requset actId:"+actId);
        QueryActivityDetailModel act = activityService.queryActWithRequiredItemId(actId);
        System.out.println("act.getRequiredItemId():"+act.getRequiredItemId());
        ActivityRequiredItem requiredItem = activityService.findRequiredItem(act.getRequiredItemId());
        System.out.println("requiredItem:"+requiredItem);
        activityService.saveBrowserHistory(userId,actId);
        boolean isIniator = activityService.isIniator(actId, userId);
        Integer init = isIniator==true?1:0;
        ActivityRequiredItemDetail detail = null;
        //参与者查看自己填写的详情
         if(init==0)
         {
             detail  = activitySignupService.getDetail(userId, actId);
         }
        if(detail!=null)
        {
            return JSONResult.success().add("act",act).add("detail",detail ).add("init",init).add("requiredItem",requiredItem);
        }else{
            return JSONResult.success().add("act",act).add("detail","0" ).add("init",init).add("requiredItem",requiredItem);
        }
    }


    @ApiOperation(value = "搜索活动按热度排行")
    @GetMapping("/search")
    public JSONResult searchActivity(@RequestParam(value="searchText",required=false) String searchText){
        System.out.println("searchText:"+searchText);
        List<QueryActivityDetailModel> act = activityService.queryActivityByTitleorderByHeat(searchText);
        System.out.println("act:"+act);
        return JSONResult.success().add("act",act);
    }


    @ApiOperation(value = "查询发起者要修改活动信息")
    @GetMapping("/edit/{actId}")
    public JSONResult editInfo(@PathVariable("actId")Integer actId){
        QueryActivityDetailModel act = activityService.queryAct(actId);
        return JSONResult.success().add("act",act);
    }


    @ApiOperation(value = "发起者修改活动信息")
    @PutMapping("/edit/{actId}")
    public JSONResult editInfo( @RequestBody ActivityWithRequiredItemModel activityInfo){
        Integer actId = activityService.updateActivityInfo(activityInfo);
        return JSONResult.success().add("actId",actId);
    }

    @ApiOperation(value = "查询所有活动种类信息")
    @GetMapping("/actCategories")
    public JSONResult getActCategories(){
        List<ActivityCategory> categories = activityService.getAllActivityCategories();
        return JSONResult.success().add("categories",categories);
    }

}
