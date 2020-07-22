package com.ls.system.enums;

/**
 * @Author: Min.Hu
 * Time: 2020/3/23 11:38
 * <p> 登录模式
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public enum LoginType {
    // 密码登录
    PASSWORD("password"),
    // 免密登录
    NOPASSWD("nopassword");
    // 状态值
    private String code;

    private LoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
