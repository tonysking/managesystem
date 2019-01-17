package com.hust.bmzsweb.managesystem.controller.web;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "活动位置信息接口",tags = "活动位置信息接口")
@RestController
@RequestMapping("/admin")
public class AdminActivityLocationController {


    @ApiOperation(value = "模糊查询所有活动位置信息")
    @GetMapping("/location/list")
    public JSONResult queryUserList(String Content, Integer type){
        return JSONResult.success();
    }



}
