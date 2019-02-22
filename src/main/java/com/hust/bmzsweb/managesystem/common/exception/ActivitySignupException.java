package com.hust.bmzsweb.managesystem.common.exception;

import com.hust.bmzsweb.managesystem.common.enums.ResultEnum;

public class ActivitySignupException extends  RuntimeException{
    private Integer code;

    public ActivitySignupException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
