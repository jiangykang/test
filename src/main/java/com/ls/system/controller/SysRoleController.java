package com.ls.system.controller;

import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.utils.ExcelUtil;
import com.ls.system.viewform.sysmenu.SysMenuEnableViewForm;
import com.ls.system.viewform.sysrole.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysRole;
import com.ls.system.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色 Controller
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author Min.Hu
 * @date 2020-03-12
 */
@Api(tags = {"系统 角色 管理"})
@RestController
@RequestMapping("/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询角色列表
     */
    @ApiOperation("查询角色列表")
    @GetMapping("/list")
    @RequiresPermissions("system:role:list")
    public ResponseResult list(SysRoleListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        IPage<SysRole> iPage = new Page<>(page,size);
        /* Wrapper<SysRole> wrapper = new QueryWrapper<>(sysrole);
        IPage<SysRole> list = sysRoleService.page(iPage, wrapper);*/
        IPage<SysRole> list = sysRoleService.selectSysRoleList(iPage,viewForm);
        return ResponseResult.success(list, "查询成功");
    }

    /**
     * 获取角色详细信息
     */
    @ApiOperation("获取角色详细信息")
    @GetMapping(value = "/{id}")
    @RequiresPermissions("system:role:query")
    public ResponseResult getInfo(@PathVariable("id") String id) {
        return ResponseResult.success(sysRoleService.getById(id));
    }

    /**
     * 新增角色
     */
    @PostMapping(value = "/insert")
    @ApiOperation("新增角色")
    @RequiresPermissions("system:role:add")
    @Log(title = "新增角色", businessType = BusinessType.INSERT)
    public ResponseResult add(@RequestBody @Validated SysRoleInsertViewForm viewForm) {
        return sysRoleService.insertSysRole(viewForm);
    }

    /**
     * 修改角色
     */
    @PutMapping(value = "/update")
    @ApiOperation("修改角色")
    @Log(title = "修改角色", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    public ResponseResult edit(@RequestBody @Validated SysRoleUpdateViewForm viewForm) {
        return sysRoleService.updateSysRole(viewForm);
    }

    /**
     * 启用停用
     */
    @PutMapping("enable")
    @ApiOperation("启用停用")
    @RequiresPermissions("system:role:enable")
    @Log(title = "启用停用角色", businessType = BusinessType.UPDATE)
    public ResponseResult enable(@Validated @RequestBody SysRoleEnableViewForm viewForm) {
        return sysRoleService.enable(viewForm);
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{ids}")
    @ApiOperation("删除角色")
    @RequiresPermissions("system:role:remove")
    @Log(title = "删除角色", businessType = BusinessType.DELETE)
    public ResponseResult remove(@PathVariable String[] ids) {
        return sysRoleService.deleteSysRoleByIds(ids);
    }


    /**
     * 修改保存数据权限
     */
    @ApiOperation("修改保存数据权限")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/dataScope")
    @RequiresPermissions("system:role:data")
    public ResponseResult dataScope(@RequestBody @Validated SysRoleAuthViewForm viewForm) {
        return sysRoleService.authDataScope(viewForm);
    }

    /**
     * 获取角色选择框列表
     */
    @GetMapping("/optionSelect")
    public ResponseResult optionSelect() {
        return ResponseResult.success(sysRoleService.selectRoleAll());
    }
}