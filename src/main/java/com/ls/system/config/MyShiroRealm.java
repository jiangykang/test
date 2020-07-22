package com.ls.system.config;


import com.ls.system.mapper.SysMenuMapper;
import com.ls.system.mapper.SysRoleMapper;
import com.ls.system.mapper.SysUserMapper;
import com.ls.system.vo.ContextUserInfo;
import com.ls.system.vo.SysRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Author: Min.Hu
 * Time: 2020/3/9 15:16
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("===========>用户授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //权限一般根据数据库的权限赋值，不会是硬编码写死的，表字段添加上权限
        ContextUserInfo userInfo = (ContextUserInfo) principalCollection.getPrimaryPrincipal();
        //设置当前用户的权限
        if (userInfo.getIsAdmin()) {
            info.addStringPermission("*:*:*");
            info.addRole("admin");
        } else {
            Set<String> roles = sysRoleMapper.selectAllRoleKeyByUserId(userInfo.getId());
            Set<String> permissions = sysMenuMapper.selectAllPermissionByUserId(userInfo.getId());
            info.addStringPermissions(permissions);
            info.addRoles(roles);
        }
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("-------身份认证方法--------");
        CustomToken upToken = (CustomToken) authenticationToken;
        //根据登录信息从数据库获取密码
        ContextUserInfo userInfo = sysUserMapper.selectUserByAccount(upToken.getUsername());
        if (userInfo == null) {
            throw new AuthenticationException("用户名不正确");
        }
        boolean adminFlag = false;
        for (SysRoleVO role : userInfo.getRoles()) {
            if ("admin".equals(role.getRoleKey())) {
                adminFlag = true;
                break;
            }
        }
        userInfo.setIsAdmin(adminFlag);
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getSalt()), getName());
    }
}
