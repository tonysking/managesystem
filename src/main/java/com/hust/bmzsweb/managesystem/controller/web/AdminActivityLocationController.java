package com.hust.bmzsweb.managesystem.controller.web;

import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动位置信息管理
 */
@Api(value = "活动位置信息接口",tags = "活动位置信息接口")
@RestController
@RequestMapping("/admin")
public class AdminActivityLocationController {

    @ApiOperation(value = "显示活动位置主页")
    @RequestMapping(value = { "/location/", "/location/index" })
    public ModelAndView index() {
        return new ModelAndView("admin/location/index");
    }

    @ApiOperation(value = "模糊查询所有活动位置信息")
    @GetMapping("/location/list")
    public JSONResult queryUserList(@RequestParam(value="searchText",required=false) String searchText, Integer type){
        return JSONResult.success();
    }



}
