package com.hust.bmzsweb.managesystem.common.exception;

import com.hust.bmzsweb.managesystem.common.enums.ResultEnum;

public class ActivityException extends  RuntimeException{
    private Integer code = 501;

    public ActivityException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ActivityException(String msg) {
        super(msg);
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
