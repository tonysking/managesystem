package com.hust.bmzsweb.managesystem.controller.web;

import com.hust.bmzsweb.managesystem.business.activity.ActivityService;
import com.hust.bmzsweb.managesystem.business.activity.model.QueryActivityListModel;
import com.hust.bmzsweb.managesystem.business.user.UsersService;
import com.hust.bmzsweb.managesystem.business.user.entity.User;
import com.hust.bmzsweb.managesystem.common.JSONResult;
import com.hust.bmzsweb.managesystem.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *    后台登录
 *     查看/搜索用户列表
 *     管理员锁定用户
 *     管理员解锁用户
 */
@Api(value = "用户接口",tags = "用户接口")
@RestController
@RequestMapping("/admin")
public class AdminUsersController extends BaseController {

    @Autowired
    UsersService usersService;

    @Autowired
    ActivityService activityService;

    @ApiOperation(value = "模糊查询所有用户")
    @GetMapping("/user/list")
    public JSONResult queryUserList(@RequestParam(value="searchText",required=false) String searchText,Integer type){
        Page<User> page = usersService.findAllByLike(searchText, getPageRequest());
        return JSONResult.success().add("page", page);
    }

    @ApiOperation(value = "后台登录")
    @GetMapping({"/login","/"})
    public ModelAndView login(){
        return new ModelAndView("admin/login");
    }

    @ApiOperation(value = "后台登录校验")
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                        @RequestParam("password") String password, HttpServletRequest request
    ) {
        System.out.println("username:"+username+" password:"+password);
        HttpSession session = request.getSession();
        System.out.println("session username:"+session.getAttribute("username"));
        if("admin".equals(username)&&"123456".equals(password))
        {
            session.setAttribute("username", username);
            return new ModelAndView("/admin/index");
        }else{
            ModelAndView modelAndView = new ModelAndView("admin/login");
            if(!"admin".equals(username))
            {
                modelAndView.addObject("error", "用户名错误");
            }else{
                modelAndView.addObject("error", "密码错误");
            }
            return modelAndView;
        }
    }

    @ApiOperation(value = "显示主页")
    @GetMapping("/index")
    public ModelAndView toIndex() {
        return new ModelAndView("admin/index");
    }

    @ApiOperation(value = "显示用户主页")
        @GetMapping({"/user/index","/user/"})
    public ModelAndView index() {
        return new ModelAndView("admin/user/index");
    }

    @ApiOperation(value = "显示用户欢迎页面")
    @GetMapping("/user/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("admin/welcome");
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        return new ModelAndView("admin/login");
    }


    @ApiOperation(value="锁定用户")
    @GetMapping("/user/{userId}/lock")
    public JSONResult lock(@PathVariable("userId")Integer userId){
        usersService.lockUserById(userId);
        return JSONResult.success();
    }

    @ApiOperation(value="解锁用户")
    @GetMapping("/user/{userId}/unLock")
    public JSONResult unLock(@PathVariable("userId")Integer userId){
        usersService.unLockUserById(userId);
        return JSONResult.success();
    }

    @ApiOperation(value="跳转到用户参与活动页面")
    @GetMapping("/user/signup")
    public ModelAndView jumpToUserSignup(Integer userId, String userNickName, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/user/signup");
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("userNickName", userNickName);
        return  modelAndView;
    }

    @ApiOperation(value="查看用户参与的活动")
    @GetMapping("/user/{userId}/signup")
    public JSONResult lookOverUserSignup(@PathVariable("userId")Integer userId){
        Page<QueryActivityListModel> page = activityService.findUserSignupAct(userId, getPageRequest());
        return JSONResult.success().add("page", page);
    }

    @ApiOperation(value="跳转到查看用户创建的活动的页面")
    @GetMapping("/user/creation")
    public ModelAndView jumpToUserCreation(Integer userId,String userNickName){
        ModelAndView modelAndView = new ModelAndView("admin/user/creation");
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("userNickName", userNickName);
        return  modelAndView;
    }

    @ApiOperation(value="查询用户创建过的活动")
    @GetMapping("/user/{userId}/creation")
    public JSONResult lookOverUserCreation(@PathVariable("userId")Integer userId){
        Page<QueryActivityListModel> page = activityService.findAllActsByUserId(userId, getPageRequest());
        return JSONResult.success().add("page", page);
    }

}
