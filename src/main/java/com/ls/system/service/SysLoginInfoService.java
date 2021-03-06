package com.ls.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.system.entity.SysDictType;
import com.ls.system.entity.SysLoginInfo;
import com.ls.system.viewform.syslogininfo.SysLoginInfoListViewForm;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * @Author: Min.Hu
 * Time: 2020/4/8 11:14
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public interface SysLoginInfoService extends IService<SysLoginInfo> {


    /**
     * 查询系统访问记录List
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    IPage<SysLoginInfo> selectLoginInfoList(Page<SysLoginInfo> iPage, SysLoginInfoListViewForm viewForm);

    /**
     * 功能描述: <br>清空登陆日志
     *
     * @param
     * @return void
     * @Author: Min.Hu
     * @Date: 2020/4/8 11:41
     */
    void cleanLoginInfo();

    /**
     * 新增系统登录日志
     *
     * @param ip
     * @param userAgent
     * @param sysLoginInfo
     */
    void insertSysLoginInfo(String ip, UserAgent userAgent, SysLoginInfo sysLoginInfo);
}
