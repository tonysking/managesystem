package com.hust.bmzsweb.managesystem.controller.applet;

import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.user.UsersRepository;
import com.hust.bmzsweb.managesystem.business.user.UsersService;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.business.user.model.UserInfoModel;
import com.hust.bmzsweb.managesystem.business.user.model.WXSessionModel;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import com.hust.bmzsweb.managesystem.common.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public JSONResult wxLogin(String code) {System.out.println("wxLogin-code: "+code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param = new HashMap<>();
        param.put("appid","wx68a522d3964051da");
        param.put("secret","fd964f98cc605ad910b800cf828390fc");
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



    //用户微信授权登录2
    @ApiOperation(value = "用户微信授权登录2")
    @GetMapping("/wxLogin2")
    public JSONResult wxLogin2(@RequestParam(value = "encryptedData") String encryptedData,@RequestParam(value = "iv") String iv, @RequestParam(value = "code") String code) throws Exception {
        System.out.println("请求的参数有:\n加密数据="+encryptedData+"\n加密算法初始向量="+iv+"\n微信小程序code="+code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param = new HashMap<>();
        param.put("appid","wx68a522d3964051da");
        param.put("secret","fd964f98cc605ad910b800cf828390fc");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        //获取{"session_key":"","openid":""}
        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);
        //将获得的json字符串转换为对象
        WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
        //获取openid和session_key
        String openid = wxSessionModel.getOpenid();
        String session_key = wxSessionModel.getSession_key();
        //存入session到redis
        redis.set(USER_REDIS_SESSION + ":" + openid,
                                            session_key,
                                1000 * 60 * 30);
        //解密用户数据
        String userInfo = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
        System.out.println("解密后的用户数据："+userInfo);

        //将解密的用户数据json转换为对象
        UserInfoModel userInfoModel = JsonUtils.jsonToPojo(userInfo, UserInfoModel.class);
        System.out.println("用户openId:"+userInfoModel.getOpenId()+"\n用户nickname:"+userInfoModel.getNickName());

        //将用户信息存入数据库
        String nickName = userInfoModel.getNickName();
        boolean UserNickNameIsExist = usersService.queryUserNickNameIsExist(nickName);
        if (!UserNickNameIsExist){
            User user = new User();
            user.setUserOpenid(openid);
            user.setUserNickName(nickName);
            usersService.saveUserInfo(user);
        }

        //将userId及用户信息传给前端
        User userByNickName = usersService.findUserByNickName(nickName);

        MD5Utils.md5(wxResult);
        return JSONResult.success().add("dec_userInfo",userInfoModel).add("userId",userByNickName.getUserId());

    }

    //查看个人信息
    @ApiOperation(value = "查看个人信息")
    @GetMapping("/getUserInfo/{userId}")
    public JSONResult getUserInfo(@PathVariable("userId") Integer userId){
        User userById = usersService.findUserById(userId);
        return JSONResult.success().add("userInfo",userById);
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
