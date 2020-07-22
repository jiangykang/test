package com.ls.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysOperLog;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.service.SysOperLogService;
import com.ls.system.viewform.sysoperlog.SysOperLogListViewForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志记录 Controller
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-01
 */
@Api(tags = {"系统 操作日志记录 管理"})
@RestController
@RequestMapping("/system/sysOperLog")
public class SysOperLogController {
    @Autowired
    private SysOperLogService sysOperLogService;

    @ApiOperation("查询操作日志")
    @GetMapping("/list")
    @RequiresPermissions("monitor:operlog:list")
    public ResponseResult list(SysOperLogListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        IPage<SysOperLog> iPage = new Page<>(page, size);
        /* Wrapper<SysOperLog> wrapper = new QueryWrapper<>(sysOperLog);
        IPage<SysOperLog> list = sysOperLogService.page(iPage, wrapper);*/
        IPage<SysOperLog> list = sysOperLogService.selectSysOperLogList(iPage, viewForm);
        return ResponseResult.success(list, "查询成功");
    }


    /**
     * 删除操作日志记录
     */
    @Log(title = "删除操作日志记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    @ApiOperation("删除操作日志记录")
    @RequiresPermissions("monitor:operlog:remove")
    public ResponseResult delete(@PathVariable String[] ids) {
        return sysOperLogService.deleteSysOperLogByIds(ids);
    }

    /**
     * 清空操作日志
     *
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空操作日志记录")
    @RequiresPermissions("monitor:operlog:clean")
    @Log(title = "清空操作日志", businessType = BusinessType.CLEAN)
    public ResponseResult clean() {
        return sysOperLogService.cleanOperLog();
    }
}
