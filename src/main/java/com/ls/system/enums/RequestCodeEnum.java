package com.ls.system.enums;

/**
 * @Author: Min.Hu
 * Time: 2020/4/27 14:05
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public enum RequestCodeEnum {
    //操作成功
    CODE200(200, "操作成功"),
    //鉴权失败
    CODE401(401, "鉴权失败"),
    //无权限
    CODE403(403, "无权限"),
    //操作失败
    CODE500(500, "操作失败");


    private final int code;

    private final String reasonPhrase;


    RequestCodeEnum(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }


    public int getCode() {
        return code;
    }


    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
