package com.ls.system.constant;


/**
 * @Author: Min.Hu
 * Time: 2020/3/10 15:24
 * <p>  信息常量
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public class Constants {

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
    /**
     * 初始密码
     */
    public static final String INITIAL_PASSWORD = "123456";

    /**
     * 是否为系统默认（是）
     */
    public static final String YES = "Y";

    /**
     * 是否 启用（是）
     */
    public static final String ENABLE = "1";
    /**
     * 是否 启用（是）
     */
    public static final String UN_ENABLE = "0";

}
