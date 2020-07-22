package com.ls.system.utils;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.ls.system.vo.ContextUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;

/**
 * @Author: Min.Hu
 * Time: 2020/3/19 11:05
 * <p>  用户信息
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public class UserInfoUtils {

    /**
     * 获取用户信息
     **/
    public static ContextUserInfo getLoginUser() {
        ContextUserInfo userInfo = new ContextUserInfo();
        try {
            userInfo = (ContextUserInfo) SecurityUtils.getSubject().getPrincipal();
            if (ObjectUtils.isEmpty(userInfo)) {
                throw new UnauthenticatedException();
            }
        } catch (Exception e) {
            throw new UnauthenticatedException();
        }
        return userInfo;
    }
}
