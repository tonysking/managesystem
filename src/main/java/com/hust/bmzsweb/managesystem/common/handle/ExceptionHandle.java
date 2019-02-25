package com.hust.bmzsweb.managesystem.common.handle;


import com.hust.bmzsweb.managesystem.common.JSONResult;

import com.hust.bmzsweb.managesystem.common.enums.ResultEnum;
import com.hust.bmzsweb.managesystem.common.exception.ActivityException;
import com.hust.bmzsweb.managesystem.common.exception.ActivitySignupException;
import com.hust.bmzsweb.managesystem.common.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



@ControllerAdvice
public class ExceptionHandle {

    Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
//    private final String ERROR_VIEW = "/error";

    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Request-With") != null && "XMLHttpRequest"
                .equals(request.getHeader("X-Request-With").toString()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handle(HttpServletRequest request,Exception e) {

            logger.error("系统异常:", e);

            if(isAjax(request))
            {
                if (e instanceof ActivityException) {
                    ActivityException  activityException= (ActivityException) e;
                    return JSONResult.fail(activityException.getMessage(),activityException.getCode());
                }else if(e instanceof UserException)
                {
                    UserException  userException= (UserException) e;
                    return JSONResult.fail(userException.getMessage(),userException.getCode());
                }else if(e instanceof ActivitySignupException)
                {
                    ActivitySignupException  activitySignupException= (ActivitySignupException) e;
                    return JSONResult.fail(activitySignupException.getMessage(),activitySignupException.getCode());
                }
                return JSONResult.fail(e.getMessage(), 500);
            }

            ModelAndView mav = new ModelAndView();
             if(e instanceof UserException){
                 mav.addObject("msg",e.getMessage());
                 mav.addObject("url",request.getRequestURL());
                 mav.setViewName("/admin/login");
                 return mav ;
             }
            mav.addObject("exception",e);
            mav.addObject("url",request.getRequestURL());
            mav.setViewName("/admin/error");
         //返回错误页面
            return mav ;



    }
}
