package com.ls.system.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ls.system.config.ResponseResult;
import com.ls.system.config.CustomToken;
import com.ls.system.entity.SysLoginInfo;
import com.ls.system.entity.SysUser;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.service.SysLoginInfoService;
import com.ls.system.service.SysUserService;
import com.ls.system.utils.*;
import com.ls.system.viewform.login.LoginViewForm;
import com.ls.system.vo.ContextUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.HashMap;
import java.util.Map;

import static com.ls.system.constant.Constants.INITIAL_PASSWORD;
import static com.ls.system.utils.RSAUtils.decrypt;

/**
 * @Author: Min.Hu
 * Time: 2020/3/9 15:24
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Api(tags = {"系统 登录相关 管理"})
@Controller
public class SysLoginController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    RedisCache redisCache;
    @Autowired
    SysLoginInfoService sysLoginInfoService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public void toLogin() {
        UserInfoUtils.getLoginUser();
    }

//    @Log(title = "用户登录", businessType = BusinessType.OTHER)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("登录")
    public ResponseResult login(@RequestBody @Validated LoginViewForm viewForm) throws Exception {
//        String verifyKey = Constants.CAPTCHA_CODE_KEY + viewForm.getUuid();
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//        if (captcha == null) {
//            throw new RuntimeException("验证码已过期");
//        }
//        if (!viewForm.getCode().equalsIgnoreCase(captcha)) {
//            throw new RuntimeException("验证码错误");
//        }

        //私钥
        String pri = redisCache.getCacheObject(viewForm.getUuid());
        if (StringUtils.isBlank(pri)) {
            throw new RuntimeException("验证码已过期");
        }
        //登录账户
        String userName = decrypt(viewForm.getUserName(), pri);
        //登录密码
        String password = decrypt(viewForm.getPassword(), pri);
        //验证码密码是否为初始密码
        boolean flag = false;
        if (INITIAL_PASSWORD.equals(password)) {
            flag = true;
        } else {
            flag = false;
        }
        // 在认证提交前准备 token（令牌）
        CustomToken token = new CustomToken(userName, password);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        System.out.println("我是token：--------------------" + token.toString());

        // 执行认证登陆
        return loginAct(subject, token, flag);
    }

    private ResponseResult loginAct(Subject subject, CustomToken token, Boolean flag) {
        subject.login(token);
        Map<String, Object> map = new HashMap<>(16);
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        SysLoginInfo sysLoginInfo = new SysLoginInfo();
        ContextUserInfo userInfo = (ContextUserInfo) subject.getPrincipal();
        sysLoginInfo.setUserName(userInfo.getUserName());
        sysLoginInfo.setMsg(subject.isAuthenticated() ? "登录成功" : "登录失败");
        sysLoginInfo.setStatus(subject.isAuthenticated() ? "0" : "1");
        sysLoginInfoService.insertSysLoginInfo(ip, userAgent, sysLoginInfo);
        if (flag != null) {
            map.put("pwdFlag", flag);
        }
        if (subject.isAuthenticated()) {
            map.put("accessToken", subject.getSession().getId());
            return ResponseResult.success(map, "登录成功");
        } else {
            token.clear();
            return ResponseResult.failure("登录失败");
        }
    }

//    @Log(title = "免密登录", businessType = BusinessType.OTHER)
    @RequestMapping(value = "/idmLogin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("免密登录")
    public ResponseResult idmLogin(String jobNumber) {
        SysUser sysUser = sysUserService.getUserInfoByJobNumber(jobNumber);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new RuntimeException("用户不存在");
        }

        CustomToken customToken = new CustomToken(jobNumber);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 执行认证登陆
        return loginAct(subject, customToken, null);
    }

    /**
     * 功能描述: <br>登出  这个方法没用到,用的是shiro默认的logout
     *
     * @param
     * @return com.ls.common.constant.ResponseResult
     * @Author: Min.Hu
     * @Date: 2020/3/23 10:51
     */
    @Log(title = "退出")
    @GetMapping("/logout")
    public ResponseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseResult.successMsg("退出成功");
    }
}
