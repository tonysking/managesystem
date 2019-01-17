package com.hust.bmzsweb.managesystem.controller.web;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "活动接口",tags = "活动接口")
@RestController
@RequestMapping("/admin")
public class AdminActivityController {
    /*
    查看/搜索活动列表
    查看活动详情
    管理员禁止活动
    管理员允许活动
    */
    @ApiOperation(value = "模糊查询所有活动")
    @GetMapping("/activity/list")
    public JSONResult queryActivities(String Content,Integer type){
        return JSONResult.success();
    }

    @ApiOperation(value = "查看活动详情")
    @GetMapping("/activity/{actId}")
    public JSONResult getUserList(@PathVariable("actId")Integer actId){
        return JSONResult.success();
    }

    @ApiOperation(value = "禁止活动")
    @GetMapping("/activity/{actId}/forbiddance")
    public JSONResult forbidActivity(@PathVariable("actId")Integer actId){
        return JSONResult.success();
    }

    @ApiOperation(value = "允许活动")
    @GetMapping("/activity/{actId}/allowance")
    public JSONResult allowActivity(@PathVariable("actId")Integer actId){
        return JSONResult.success();
    }



}
