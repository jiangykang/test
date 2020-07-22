package com.ls.tool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.tool.entity.GenTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author lsbdp
 */
public interface GenTableMapper extends BaseMapper<GenTable> {
    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    public IPage<GenTable> selectGenTableList(Page<GenTable> page, GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param page
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    public IPage<GenTable> selectDbTableList(Page<GenTable> page, GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    public GenTable selectGenTableByName(String tableName);

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    public int insertGenTable(GenTable genTable);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    public int updateGenTable(@Param(value = "genTable") GenTable genTable);

    /**
     * 批量删除业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenTableByIds(Long[] ids);
}