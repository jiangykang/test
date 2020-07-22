package com.ls.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDictType;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.service.SysDictTypeService;
import com.ls.system.viewform.sysdicttype.SysDictTypeInsertViewForm;
import com.ls.system.viewform.sysdicttype.SysDictTypeListViewForm;
import com.ls.system.viewform.sysdicttype.SysDictTypeUpdateViewForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 *
 * @author lsbdp
 */
@Api(tags = {"系统 数据字典类型信息"})
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {
    @Autowired
    private SysDictTypeService sysDictTypeService;

    @GetMapping("/list")
    @ApiOperation("查询数据字典类型list")
    @RequiresPermissions("system:dict:list")
    public ResponseResult list(SysDictTypeListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        IPage<SysDictType> iPage = new Page<>(page, size);
        /* Wrapper<SysDictType> wrapper = new QueryWrapper<>(sysDictType);
        IPage<SysDictType> list = sysDictTypeService.page(iPage, wrapper);*/
        IPage<SysDictType> list = sysDictTypeService.selectSysDictTypeList(iPage, viewForm);
        return ResponseResult.success(list, "查询成功");
    }


    /**
     * 查询字典类型详细
     */
    @ApiOperation("查询字典类型详细")
    @GetMapping(value = "/{id}")
    @RequiresPermissions("system:dict:query")
    public ResponseResult getInfo(@PathVariable String id) {
        return ResponseResult.success(sysDictTypeService.getById(id));
    }

    /**
     * 新增字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增字典类型")
    @RequiresPermissions("system:dict:add")
    public ResponseResult add(@Validated @RequestBody SysDictTypeInsertViewForm viewForm) {

        return sysDictTypeService.insertSysDictType(viewForm);
    }

    /**
     * 修改字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改字典类型")
    @RequiresPermissions("system:dict:edit")
    public ResponseResult edit(@Validated @RequestBody SysDictTypeUpdateViewForm viewForm) {

        return sysDictTypeService.updateSysDictType(viewForm);
    }

    /**
     * 删除字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    @ApiOperation("删除字典类型")
    @RequiresPermissions("system:dict:remove")
    public ResponseResult remove(@PathVariable String[] dictIds) {
        return sysDictTypeService.deleteSysDictTypeByIds(dictIds);
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionSelect")
    @ApiOperation("获取字典选择框列表")
    public ResponseResult optionSelect() {
        List<SysDictType> dictTypes = sysDictTypeService.selectDictTypeAll();
        return ResponseResult.success(dictTypes);
    }
}
