package com.ls.tool.controller;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.config.ResponseResult;
import com.ls.tool.entity.GenTable;
import com.ls.tool.entity.GenTableColumn;
import com.ls.tool.service.IGenTableColumnService;
import com.ls.tool.service.IGenTableService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author lsbdp
 */
@Api(tags = {"系统 代碼 生成"})
@RestController
@RequestMapping("/tool/gen")
public class GenController {

    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public IPage<GenTable> genList(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size, GenTable genTable) {
        Page<GenTable> tablePage = new Page<>(page, size);
        IPage<GenTable> list = genTableService.selectGenTableList(tablePage, genTable);
        return list;
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{talbleId}")
    public ResponseResult getInfo(@PathVariable Long talbleId) {
        GenTable table = genTableService.selectGenTableById(talbleId);
        Page<GenTableColumn> page = new Page<>(1, 9999);
        IPage<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(page, talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list.getRecords());
        return ResponseResult.success(map);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public IPage<GenTable> dataList(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size, GenTable genTable) {
        Page<GenTable> tablePage = new Page<>(page, size);
        IPage<GenTable> list = genTableService.selectDbTableList(tablePage, genTable);
        return list;
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{talbleId}")
    public IPage<GenTableColumn> columnList(@RequestParam(value = "page") Long page, @RequestParam(value = "size") Long size, Long tableId) {
        Page<GenTableColumn> page1 = new Page<>(page, size);
        IPage<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(page1, tableId);

        return list;
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public ResponseResult importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return ResponseResult.success();
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public ResponseResult editSave(@Validated @RequestBody GenTable genTable) {
//        System.out.println(genTable.getParams().size());
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return ResponseResult.success();
    }

    @DeleteMapping("/{tableIds}")
    public ResponseResult remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return ResponseResult.success();
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public ResponseResult preview(@PathVariable("tableId") Long tableId) {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return ResponseResult.success(dataMap);
    }

    /**
     * 生成代码
     */
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.generatorCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"lsbdp.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}