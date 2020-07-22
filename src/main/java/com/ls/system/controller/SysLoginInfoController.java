package com.ls.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDictType;
import com.ls.system.entity.SysLoginInfo;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.service.SysLoginInfoService;
import com.ls.system.viewform.syslogininfo.SysLoginInfoListViewForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author lsbdp
 */
@Api(tags = {"系统 访问记录"})
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLoginInfoController {
    @Autowired
    private SysLoginInfoService sysLoginInfoService;


    /**
     * 查询系统访问记录List
     *
     * @param viewForm
     * @param page
     * @param size
     * @return
     */
    @ApiOperation("查询系统访问记录")
    @GetMapping("/list")
    @RequiresPermissions("monitor:logininfor:list")
    public ResponseResult list(SysLoginInfoListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<SysLoginInfo> iPage = new Page<>(page, size);
        IPage<SysLoginInfo> list = sysLoginInfoService.selectLoginInfoList(iPage, viewForm);
        return ResponseResult.success(list);
    }

    /**
     * 删除登陆日志
     *
     * @param infoIds
     * @return
     */
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    @ApiOperation("删除登陆日志")
    @RequiresPermissions("monitor:logininfor:remove")
    public ResponseResult remove(@PathVariable String[] infoIds) {
        List<String> list = Arrays.asList(infoIds);
        boolean b = sysLoginInfoService.removeByIds(list);
        if (b) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.success("删除失败");
        }
    }

    /**
     * 清空登陆日志
     *
     * @return
     */
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @ApiOperation("清空登陆日志")
    @DeleteMapping("/clean")
    @RequiresPermissions("monitor:logininfor:clean")
    public ResponseResult clean() {
        sysLoginInfoService.cleanLoginInfo();
        return ResponseResult.success("清空成功");
    }
}
