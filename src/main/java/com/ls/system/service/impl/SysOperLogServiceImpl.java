package com.ls.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysOperLog;
import com.ls.system.mapper.SysOperLogMapper;
import com.ls.system.service.SysOperLogService;
import com.ls.system.utils.GenerateIdUtil;
import com.ls.system.viewform.sysoperlog.SysOperLogListViewForm;
import com.ls.system.vo.ContextUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 操作日志记录Service业务层处理
 *
 * @Author Min.Hu
 * @date 2020-04-01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    /**
     * 查询操作日志记录列表
     *
     * @param sysOperLog 操作日志记录
     * @return 操作日志记录
     */
    @Override
    public IPage<SysOperLog> selectSysOperLogList(IPage<SysOperLog> iPage, SysOperLogListViewForm viewForm) {
        return sysOperLogMapper.selectSysOperLogList(iPage, viewForm);
    }


    /**
     * 新增操作日志记录
     *
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    @Async("task")
    @Override
    public ResponseResult insertLog(SysOperLog operLog) {
        SecurityUtils.getSecurityManager();
        // 获取当前的用户
        ContextUserInfo userInfo = (ContextUserInfo) SecurityUtils.getSubject().getPrincipal();
        if (ObjectUtils.isEmpty(userInfo)) {
            operLog.setOperName(null);
            operLog.setDeptName(null);
        } else {
            operLog.setOperName(userInfo.getUserName());
            operLog.setDeptName(userInfo.getDepts().getDeptName());
        }
        operLog.setId(GenerateIdUtil.generateId());
        operLog.setOperTime(LocalDateTime.now());
        sysOperLogMapper.insert(operLog);
        return ResponseResult.success("新增成功");
    }


    /**
     * 批量删除操作日志记录
     *
     * @param ids 需要删除的操作日志记录ID
     * @return 结果
     */
    @Override
    public ResponseResult deleteSysOperLogByIds(String[] ids) {

        List<String> list = Arrays.asList(ids);
        //此处是真实删除  后期根据业务 可自由定义成逻辑删除
        sysOperLogMapper.deleteBatchIds(list);
        return ResponseResult.success("删除成功");
    }

    /**
     * 清空操作日志
     *
     * @return
     */
    @Override
    public ResponseResult cleanOperLog() {
        sysOperLogMapper.cleanOperLog();
        return ResponseResult.successMsg("清空操作日志");
    }

}
