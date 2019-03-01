package com.hust.bmzsweb.managesystem.common.enums;

public enum HeatEnum {
    Search(3,1.1,"搜索活动"),
    LookOver(1,1.05,"查看活动"),
    SignUp(10,1.2,"报名活动")
    ;

    private int count;         //热度参数
    private double propotion; //热度系数
    private String msg;

    HeatEnum(int count,double propotion, String msg) {
        this.count = count;
        this.propotion = propotion;
        this.msg = msg;
    }

    public double getPropotion() {
        return propotion;
    }

    public void setPropotion(double propotion) {
        this.propotion = propotion;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
