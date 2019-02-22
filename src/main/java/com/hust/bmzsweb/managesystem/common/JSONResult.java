package com.hust.bmzsweb.managesystem.common;

import com.hust.bmzsweb.managesystem.common.enums.ResultEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JSONResult {

    //状态码 100-成功 200-失败 -1-未知错误
    private int code;
    //提示信息
    private String msg;
    //用户要返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<>();

    public static JSONResult success() {
        JSONResult result = new JSONResult();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    public static JSONResult fail() {
        JSONResult result = new JSONResult();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    public static JSONResult fail(String errorMsg,Integer code) {
        JSONResult result = new JSONResult();
        result.setCode(code);
        result.setMsg(errorMsg);
        return result;
    }

    public static JSONResult fail(ResultEnum resultEnum) {
        JSONResult result = new JSONResult();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }




    public JSONResult add(String key, Object value) {
        this.getExtend().put(key,value);
        return this;
    }
}
