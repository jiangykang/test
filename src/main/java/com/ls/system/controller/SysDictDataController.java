package com.ls.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDictData;
import com.ls.system.enums.BusinessType;
import com.ls.system.interceptor.annotation.Log;
import com.ls.system.service.SysDictDataService;
import com.ls.system.viewform.sysdictdata.SysDictDataInsertViewForm;
import com.ls.system.viewform.sysdictdata.SysDictDataListViewForm;
import com.ls.system.viewform.sysdictdata.SysDictDataUpdateViewForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典信息
 *
 * @author lsbdp
 */
@Api(tags = {"系统 数据字典信息"})
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController {
    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 查询字典数据列表
     */
    @ApiOperation("查询字典数据列表")
    @GetMapping("/list")
    public ResponseResult list(SysDictDataListViewForm viewForm, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        IPage<SysDictData> iPage = new Page<>(page, size);
        /* Wrapper<SysDictData> wrapper = new QueryWrapper<>(sysdictdata);
        IPage<SysDictData> list = sysDictDataService.page(iPage, wrapper);*/
        IPage<SysDictData> list = sysDictDataService.selectSysDictDataList(iPage, viewForm);
        return ResponseResult.success(list, "查询成功");
    }

    /**
     * 查询字典数据详细
     */
    @ApiOperation("查询字典数据详细")
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable String id) {
        return ResponseResult.success(sysDictDataService.getById(id));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation("根据字典类型查询字典数据信息")
    @GetMapping(value = "/dictType/{dictType}")
    public ResponseResult dictType(@PathVariable String dictType) {
        return ResponseResult.success(sysDictDataService.selectDictDataByType(dictType));
    }

    /**
     * 新增字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增字典类型")
    public ResponseResult add(@Validated @RequestBody SysDictDataInsertViewForm viewForm) {
        return sysDictDataService.insertSysDictData(viewForm);
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改保存字典类型")
    public ResponseResult edit(@Validated @RequestBody SysDictDataUpdateViewForm viewForm) {
        return sysDictDataService.updateSysDictData(viewForm);
    }

    /**
     * 删除字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除字典类型")
    public ResponseResult remove(@PathVariable String[] ids) {
        return sysDictDataService.deleteSysDictDataByIds(ids);
    }
}
