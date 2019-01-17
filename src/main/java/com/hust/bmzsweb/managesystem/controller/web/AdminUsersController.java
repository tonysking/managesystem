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

@Api(value = "用户接口",tags = "用户接口")
@RestController
@RequestMapping("/admin")
public class AdminUsersController {
    /*
    后台登录
    查看/搜索用户列表
    管理员锁定用户
    管理员解锁用户
    */
    @ApiOperation(value = "模糊查询所有用户")
    @GetMapping("/user/list")
    public JSONResult queryUserList(String Content,Integer type){
        List<User> users = new ArrayList<>();
        User user1 = new User("xn123");
        User user2 = new User("cg123");
        users.add(user1);
        users.add(user2);
        return JSONResult.success().add("users", users);
    }

    @ApiOperation(value = "后台登录")
    @GetMapping("/login")
    public JSONResult login(){
        return JSONResult.success();
    }

    @ApiOperation(value="锁定用户")
    @GetMapping("/user/{userId}/lock")
    public JSONResult lock(@PathVariable("userId")Integer userId){
        return JSONResult.success();
    }

    @ApiOperation(value="解锁用户")
    @GetMapping("/user/{userId}/unLock")
    public JSONResult unLock(@PathVariable("userId")Integer userId){
        return JSONResult.success();
    }

    @ApiOperation(value="查看用户参与的活动")
    @GetMapping("/user/{userId}/signup ")
    public JSONResult lookOverUserSignup(@PathVariable("userId")Integer userId){
        return JSONResult.success();
    }

    @ApiOperation(value="查看用户发起的活动")
    @GetMapping("/user/{userId}/creation ")
    public JSONResult lookOverUserCreation(@PathVariable("userId")Integer userId){
        return JSONResult.success();
    }




}
