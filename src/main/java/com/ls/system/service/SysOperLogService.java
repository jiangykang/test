package com.ls.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysOperLog;
import com.ls.system.viewform.sysoperlog.SysOperLogListViewForm;

/**
 * 操作日志记录Service接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-01
 */
public interface SysOperLogService extends IService<SysOperLog> {


    /**
     * @param iPage    分页
     * @param viewForm 操作日志记录
     * @return 操作日志记录集合
     */
    public IPage<SysOperLog> selectSysOperLogList(IPage<SysOperLog> iPage, SysOperLogListViewForm viewForm);

    /**
     * 批量删除操作日志记录
     *
     * @param ids 需要删除的操作日志记录ID
     * @return 结果
     */
    ResponseResult deleteSysOperLogByIds(String[] ids);

    /**
     * 新增操作日志记录
     *
     * @param sysOperLog
     * @return
     */
    ResponseResult insertLog(SysOperLog sysOperLog);

    /**
     * 清空操作日志
     *
     * @return
     */
    ResponseResult cleanOperLog();
}
