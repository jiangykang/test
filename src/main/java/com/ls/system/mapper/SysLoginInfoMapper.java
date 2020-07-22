package com.ls.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.entity.SysDictType;
import com.ls.system.entity.SysLoginInfo;
import com.ls.system.viewform.syslogininfo.SysLoginInfoListViewForm;

/**
 * @Author: Min.Hu
 * Time: 2020/4/8 11:15
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public interface SysLoginInfoMapper extends BaseMapper<SysLoginInfo> {
    /**
     * 查询系统访问记录List
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    IPage<SysLoginInfo> selectLoginInfoList(Page<SysLoginInfo> iPage, SysLoginInfoListViewForm viewForm);

    /**
     * 清空登陆日志
     */
    void cleanLoginInfo();
}
