package com.ls.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysUser;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.service.SysUserService;
import com.ls.system.viewform.sysuser.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 13:46
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Api(tags = {"系统 用户 管理"})
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户列表
     */
    @ApiOperation("查询用户列表")
    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    public ResponseResult list(SysUserListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        Page<SysUser> iPage = new Page<>(page, size);
        /* Wrapper<SysUser> wrapper = new QueryWrapper<>(sysuser);
        IPage<SysUser> list = sysUserService.page(iPage, wrapper);*/
        IPage<SysUser> list = sysUserService.selectSysUserList(iPage, viewForm);
        return ResponseResult.success(list, "查询成功");
    }

    /**
     * 获取用户详细信息
     */
    @ApiOperation("获取用户详细信息")
    @GetMapping(value = "/{id}")
    @RequiresPermissions("system:user:query")
    public ResponseResult getInfo(@PathVariable("id") String id) {
        return ResponseResult.success(sysUserService.getById(id));
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "/insert")
    @ApiOperation("新增用户")
    @RequiresPermissions("system:user:add")
    @Log(title = "新增用户", businessType = BusinessType.INSERT)
    public ResponseResult add(@RequestBody @Validated SysUserInsertViewForm viewForm) {
        return sysUserService.insertSysUser(viewForm);
    }

    /**
     * 修改用户
     */
    @PutMapping(value = "/update")
    @ApiOperation("修改用户")
    @RequiresPermissions("system:user:edit")
    @Log(title = "修改用户", businessType = BusinessType.UPDATE)
    public ResponseResult edit(@RequestBody @Validated SysUserUpdateViewForm viewForm) {
        return sysUserService.updateSysUser(viewForm);
    }
    /**
     * 启用停用
     */
    @PutMapping("enable")
    @ApiOperation("启用停用")
    @Log(title = "启用停用用户", businessType = BusinessType.UPDATE)
    public ResponseResult enable(@Validated @RequestBody SysUserEnableViewForm viewForm) {
        return sysUserService.enable(viewForm);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{ids}")
    @ApiOperation("删除用户")
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:user:remove")
    public ResponseResult remove(@PathVariable String[] ids) {
        return sysUserService.deleteSysUserByIds(ids);
    }

    /**
     * 获取用户详细信息
     */
    @ApiOperation("登录成功后获取用户详细信息")
    @GetMapping(value = "/query")
    public ResponseResult getLoginUser() {
        return ResponseResult.success(sysUserService.getLoginUser());
    }

    /**
     * 重置密码
     */
    @PutMapping("/resetPwd")
    @ApiOperation("重置密码")
    @Log(title = "重置密码", businessType = BusinessType.OTHER)
    @RequiresPermissions("system:user:resetPwd")
    public ResponseResult resetPwd(@RequestBody String id) {
        return sysUserService.resetPwd(id);
    }

    /**
     * 修改密码
     *
     * @param viewForm
     * @return
     * @throws Exception
     */
    @PutMapping("/editPwd")
    @ApiOperation("修改密码")
    @Log(title = "修改密码", businessType = BusinessType.OTHER)
    public ResponseResult editPwd(@RequestBody SysUserEditPwdViewForm viewForm) throws Exception {
        return sysUserService.editPwd(viewForm);
    }

    /**
     * 修改密码前获取公钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    @GetMapping("/getPubKey")
    @ApiOperation("修改密码前获取公钥")
    public ResponseResult getPubKey() throws NoSuchAlgorithmException {
        return sysUserService.getPubKey();
    }

    /**
     * 上传头像
     *
     * @return
     */
    @PostMapping("/uploadImg")
    @ApiOperation("上传头像")
    public ResponseResult uploadImg(MultipartFile file)  {
        return sysUserService.uploadImg(file);
    }

}
