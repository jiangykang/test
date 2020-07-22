package com.ls.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ls.system.entity.SysOperLog;
import com.ls.system.viewform.sysoperlog.SysOperLogListViewForm;
import org.springframework.stereotype.Repository;

/**
 * 操作日志记录Mapper接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-01
 */
@Repository
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    /**
     * 查询操作日志记录列表
     *
     * @param iPage    分页
     * @param viewForm 操作日志记录
     * @return 操作日志记录集合
     */
    IPage<SysOperLog> selectSysOperLogList(IPage<SysOperLog> iPage, SysOperLogListViewForm viewForm);

    void cleanOperLog();
}
