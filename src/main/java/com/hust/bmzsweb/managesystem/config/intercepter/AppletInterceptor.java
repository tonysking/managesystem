package com.hust.bmzsweb.managesystem.config.intercepter;

import com.hust.bmzsweb.managesystem.common.JSONResult;
import com.hust.bmzsweb.managesystem.common.utils.JsonUtils;
import com.hust.bmzsweb.managesystem.common.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class AppletInterceptor implements HandlerInterceptor {

    @Autowired
    RedisOperator redisOperator;
    public static final String USER_REDIS_SESSION = "user-redis-session";


    /**
     * 在controller调用之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头中的headerOpenId
        String openId = request.getHeader("headerOpenId");
        //查询redis中是否存在对应的用户信息
        if (StringUtils.isNotBlank(openId)) {
            String userSessionKey = redisOperator.get(USER_REDIS_SESSION + ":" + openId);
            if (StringUtils.isEmpty(userSessionKey)) {
                log.info("请登录...");
                returnErrorResponse(response, new JSONResult().fail().add("未登录","授权过期"));
                return false;
            }

        } else {
            log.info("请登录...");
            returnErrorResponse(response, new JSONResult().fail().add("未登录","未授权"));
            return false;
        }

        /**
         * 返回 false：请求被拦截，返回
         * 返回 true ：请求OK，可以继续执行，放行
         */
        log.info("请求通过，继续执行...");
        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, JSONResult result) throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
