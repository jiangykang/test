package com.ls.system.config;

import com.ls.system.enums.LoginType;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Min.Hu
 * Time: 2020/3/23 11:40
 * <p>免密登录配置
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Configuration
public class MyRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        CustomToken tk = (CustomToken) authcToken;
        if(tk.getType().equals(LoginType.NOPASSWD)){
            return true;
        }
        boolean matches = super.doCredentialsMatch(authcToken, info);
        return matches;
    }


}
