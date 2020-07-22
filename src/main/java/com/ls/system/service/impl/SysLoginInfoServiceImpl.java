package com.ls.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.utils.GenerateIdUtil;
import com.ls.system.entity.SysDictType;
import com.ls.system.entity.SysLoginInfo;
import com.ls.system.mapper.SysLoginInfoMapper;
import com.ls.system.service.SysLoginInfoService;
import com.ls.system.utils.AddressUtils;
import com.ls.system.utils.GenerateIdUtil;
import com.ls.system.utils.IpUtils;
import com.ls.system.utils.ServletUtils;
import com.ls.system.viewform.syslogininfo.SysLoginInfoListViewForm;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: Min.Hu
 * Time: 2020/4/8 11:15
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo> implements SysLoginInfoService {

    @Autowired
    SysLoginInfoMapper sysLoginInfoMapper;

    /**
     * 新增系统登录日志
     *
     * @param sysLoginInfo 访问日志对象
     */
    @Override
    @Async("task")
    public void insertSysLoginInfo(String ip, UserAgent userAgent, SysLoginInfo sysLoginInfo) {
        sysLoginInfo.setInfoId(GenerateIdUtil.generateId());
        String address = AddressUtils.getRealAddressByIP(ip);
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();

        sysLoginInfo.setIpAdd(ip);
        sysLoginInfo.setLoginLocation(address);
        sysLoginInfo.setBrowser(browser);
        sysLoginInfo.setOs(os);
        sysLoginInfo.setLoginTime(LocalDateTime.now());
        sysLoginInfoMapper.insert(sysLoginInfo);
    }


    /**
     * 查询系统访问记录List
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    @Override
    public IPage<SysLoginInfo> selectLoginInfoList(Page<SysLoginInfo> iPage, SysLoginInfoListViewForm viewForm) {

        return sysLoginInfoMapper.selectLoginInfoList(iPage, viewForm);
    }

    /**
     * 清空登陆日志
     */
    @Override
    public void cleanLoginInfo() {
        sysLoginInfoMapper.cleanLoginInfo();
    }
}
