package com.ls.system.controller;

import java.util.List;

import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.mapstruct.SysDeptMapStruct;
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
import com.ls.system.entity.SysDept;
import com.ls.system.entity.common.TreeSelect;
import com.ls.system.service.SysDeptService;
import com.ls.system.viewform.sysdept.SysDeptInsertViewForm;
import com.ls.system.viewform.sysdept.SysDeptListViewForm;
import com.ls.system.viewform.sysdept.SysDeptUpdateViewForm;
import com.ls.system.vo.SysDeptVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 部门 Controller
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author MIn.Hu
 * @date 2020-03-16
 */
@Api(tags = {"系统 部门 管理"})
@RestController
@RequestMapping("/system/sysDept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 查询部门列表
     */
    @ApiOperation("查询部门列表")
    @GetMapping("/list")
    @RequiresPermissions("system:dept:list")
    public ResponseResult list(SysDeptListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        IPage<SysDept> iPage = new Page<>(page, size);
        SysDept sysDept = SysDeptMapStruct.INSTANCE.listViewFormToSysDept(viewForm);
        /* Wrapper<SysDept> wrapper = new QueryWrapper<>(sysdept);
        IPage<SysDept> list = sysDeptService.page(iPage, wrapper);*/
        IPage<SysDept> list = sysDeptService.selectSysDeptList(sysDept, iPage);
        return ResponseResult.success(list, "查询成功");
    }

    /**
     * 获取部门详细信息
     */
    @ApiOperation("获取部门详细信息")
    @GetMapping(value = "/info/{id}")
    public ResponseResult info(@PathVariable("id") String id) {
        return ResponseResult.success(sysDeptService.getById(id));
    }

    /**
     * 获取部门下拉树列表
     */
    @ApiOperation("获取部门下拉树列表")
    @GetMapping("/treeSelect")
    public ResponseResult treeSelect(SysDeptListViewForm viewForm) {
        SysDept sysDept = SysDeptMapStruct.INSTANCE.listViewFormToSysDept(viewForm);

        List<TreeSelect> list = sysDeptService.selectDeptList(sysDept);
        return ResponseResult.success(list, "查询成功");
    }

    /**
     * 加载对应角色部门列表树
     */
    @ApiOperation("加载对应角色部门列表树")
    @GetMapping(value = "/roleDeptTreeSelect/{roleId}")
    public ResponseResult roleDeptTreeSelect(@PathVariable("roleId") String roleId) {
        return ResponseResult.success(sysDeptService.selectDeptListByRoleId(roleId));
    }

    /**
     * 新增部门
     */
    @Log(title = "新增部门", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insert")
    @ApiOperation("新增部门")
    @RequiresPermissions("system:dept:add")
    public ResponseResult insert(@RequestBody @Validated SysDeptInsertViewForm viewForm) {
        return sysDeptService.insertSysDept(viewForm);
    }

    /**
     * 修改部门
     */
    @Log(title = "修改部门", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/update")
    @ApiOperation("修改部门")
    @RequiresPermissions("system:dept:edit")
    public ResponseResult update(@RequestBody @Validated SysDeptUpdateViewForm viewForm) {
        return sysDeptService.updateSysDept(viewForm);
    }

    /**
     * 删除部门
     */
    @Log(title = "删除部门", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除部门")
    @RequiresPermissions("system:dept:remove")
    public ResponseResult delete(@PathVariable String id) {
        if (sysDeptService.deleteSysDeptById(id) > 0) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure("删除失败");
        }
    }


    /**
     * 根据父id获取部门
     */
    @ApiOperation("根据父id获取部门")
    @GetMapping("/getListByParentId")
    public ResponseResult getListByParentId(String id) {
        SysDept sysDept=new SysDept();
        sysDept.setParentId(id);
        List<SysDeptVO> list = sysDeptService.getListByParentId(sysDept);
        return ResponseResult.success(list, "查询成功");
    }

}
