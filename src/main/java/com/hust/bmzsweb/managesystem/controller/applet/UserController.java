package com.hust.bmzsweb.managesystem.controller.applet;

import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.user.UsersService;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.business.user.entity.WXUser;
import com.hust.bmzsweb.managesystem.business.user.model.WXSessionModel;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import com.hust.bmzsweb.managesystem.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.bouncycastle.jce.provider.symmetric.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  用户微信授权登录
 *  修改个人信息
 *  查看浏览历史
 *  查看发起的活动
 *  查看参与的活动
 *  额外功能： 发起者审核报名者信息，删除报名成员，
 */
@Api(value = "小程序用户接口",tags = "小程序用户接口")
@RestController
@RequestMapping("/applet/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RedisOperator redis;
    public static final String USER_REDIS_SESSION = "user-redis-session";

    //用户微信授权登录
    @ApiOperation(value = "用户微信授权登录")
    @PostMapping("/wxLogin")
    public JSONResult wxLogin(String code) {
        System.out.println("wxLogin-code: "+code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param = new HashMap<>();
        param.put("appid","wxc6467a1616a9bf95");
        param.put("secret","c2c3b087848e9c806bc72e8564ae08af");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        //获取{"session_key":"","openid":""}
        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);
        //将获得的json字符串转换为对象
        WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
        //存入session到redis
        redis.set(USER_REDIS_SESSION + ":" + wxSessionModel.getOpenid(),
                wxSessionModel.getSession_key(),
                1000 * 60 * 30);
        MD5Utils.md5(wxResult);
        return JSONResult.success().add("MD5_wxResult",wxResult);

    }

    @ApiOperation("添加一个用户到数据库")
    @PostMapping("/add")
    public JSONResult addUser(String nickName,String openId){
        System.out.println(nickName);
        System.out.println(openId);
        Integer userId = usersService.saveUser(nickName,openId);
        return JSONResult.success().add("userId",userId);
    }


    //查看个人信息
    @ApiOperation(value = "查看个人信息")
    @GetMapping("/getUserInfo")
    public JSONResult getUserInfo( Integer openId){

        User userInfo = usersService.findUserByOpenId(openId);
        return JSONResult.success().add("userInfo",userInfo);
    }

    //修改个人信息
    @ApiOperation(value = "修改个人信息")
    @PostMapping("/updateUserInfo")
    public JSONResult modifyUserInfo(@RequestBody User user){
        usersService.updateUserInfo(user);
        return JSONResult.success().add("updateUser",user);
    }
    //查看浏览历史
    @GetMapping("/getUserBrowsingHistory/{userId}")
    public JSONResult getUserBrowsingHistory(@PathVariable("userId") Integer userId){

        return JSONResult.success();
    }
    //查看发起的活动
    @ApiOperation(value = "查看发起的活动")
    @GetMapping("/lookOverUserCreation/{userId}")
    public JSONResult lookOverUserSignup(@PathVariable("userId") Integer userId){
        List<QueryActivityListModel> userCreateAct = usersService.findUserCreateAct(userId);
        return JSONResult.success().add("userCreateActList",userCreateAct);
    }
    //查看参与的活动
    @ApiOperation(value = "查看参与的活动")
    @GetMapping("/lookOverUserSignup/{userId}")
    public JSONResult lookOverUserCreation(@PathVariable("userId") Integer userId){
        List<QueryActivityListModel> userSignupAct = usersService.findUserSignupAct(userId);
        return JSONResult.success().add("userSignupActList",userSignupAct);
    }
}
