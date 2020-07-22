package com.ls.system.controller;

import java.util.List;

import com.ls.system.entity.common.SysResourceTree;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.viewform.sysmenu.SysMenuEnableViewForm;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ls.system.config.ResponseResult;
import com.ls.system.service.SysMenuService;
import com.ls.system.viewform.sysmenu.SysMenuInsertViewForm;
import com.ls.system.viewform.sysmenu.SysMenuListViewForm;
import com.ls.system.viewform.sysmenu.SysMenuUpdateViewForm;
import com.ls.system.vo.SysMenuVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单 Controller
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-12
 */
@Api(tags = {"系统 菜单 管理"})
@RestController
@RequestMapping("/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 登录成功后根据登录人获取菜单详细信息
     */
    @ApiOperation("登录成功后根据登录人获取菜单详细信息")
    @GetMapping(value = "/query")
    public ResponseResult query() {
        return ResponseResult.success(sysMenuService.getLoginUserMenu());
    }

    /**
     * 登录成功后根据登录人获取菜单详细信息
     */
    @ApiOperation("登录成功后根据登录人获取菜单详细信息vue")
    @GetMapping(value = "/queryVue")
    public ResponseResult queryVue() {
        return ResponseResult.success(sysMenuService.getLoginUserMenuVue());
    }

    /**
     * 获取菜单列表
     */
    @ApiOperation("获取菜单列表树型")
    @GetMapping("/tree")
    public ResponseResult tree(SysMenuListViewForm viewForm) {
        List<SysMenuVO> list = sysMenuService.selectMenuList(viewForm);
        return ResponseResult.success(sysMenuService.buildMenuTree(list));
    }

    /**
     * 获取菜单列表
     */
    @ApiOperation("获取菜单列表")
    @GetMapping("/list")
    @RequiresPermissions("system:menu:list")
    public ResponseResult list(SysMenuListViewForm viewForm) {
        List<SysMenuVO> list = sysMenuService.selectMenuList(viewForm);
        return ResponseResult.success(list);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @ApiOperation("根据菜单id获取详细信息")
    @GetMapping(value = "/{id}")
    @RequiresPermissions("system:menu:query")
    public ResponseResult getInfo(@PathVariable String id) {
        return ResponseResult.success(sysMenuService.getById(id));
    }

    /**
     * 获取菜单下拉树列表
     */
    @ApiOperation("获取菜单下拉树列表")
    @GetMapping("/treeSelect")
    public ResponseResult treeSelect(SysMenuListViewForm viewForm) {
        List<SysMenuVO> menus = sysMenuService.selectMenuList(viewForm);
        return ResponseResult.success(sysMenuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @ApiOperation("加载对应角色菜单列表树")
    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    public ResponseResult roleMenuTreeSelect(@PathVariable("roleId") String roleId) {
        return ResponseResult.success(sysMenuService.selectMenuIdListByRoleId(roleId));
    }

    /**
     * 新增菜单
     */
    @PostMapping("insert")
    @ApiOperation("新增菜单")
    @RequiresPermissions("system:menu:add")
    @Log(title = "新增菜单", businessType = BusinessType.INSERT)
    public ResponseResult insert(@Validated @RequestBody SysMenuInsertViewForm viewForm) {

        if (sysMenuService.insertMenu(viewForm) > 0) {
            return ResponseResult.success("新增成功");
        } else {
            return ResponseResult.failure("新增失败");
        }

    }

    /**
     * 修改菜单
     */
    @PutMapping("update")
    @ApiOperation("修改菜单")
    @RequiresPermissions("system:menu:edit")
    @Log(title = "修改菜单", businessType = BusinessType.UPDATE)
    public ResponseResult edit(@Validated @RequestBody SysMenuUpdateViewForm viewForm) {
        if (sysMenuService.updateMenu(viewForm) > 0) {
            return ResponseResult.success("修改成功");
        } else {
            return ResponseResult.failure("修改失败");
        }
    }

    /**
     * 启用停用
     */
    @PutMapping("enable")
    @ApiOperation("启用停用")
    @Log(title = "启用停用菜单", businessType = BusinessType.UPDATE)
    public ResponseResult enable(@Validated @RequestBody SysMenuEnableViewForm viewForm) {
        return sysMenuService.enable(viewForm);
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除菜单")
    @RequiresPermissions("system:menu:remove")
    @Log(title = "删除菜单", businessType = BusinessType.DELETE)
    public ResponseResult remove(@PathVariable("id") String id) {
        if (sysMenuService.deleteMenuById(id) > 0) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure("删除失败");
        }
    }
}