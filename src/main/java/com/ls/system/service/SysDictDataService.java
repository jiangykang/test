package com.ls.system.service;

import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDictData;
import com.ls.system.viewform.sysdictdata.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 字典数据Service接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
public interface SysDictDataService extends IService<SysDictData> {

    /**
     * 查询字典数据列表
     *
     * @param viewForm 字典数据
     * @return 字典数据集合
     */
    public IPage<SysDictData> selectSysDictDataList(IPage<SysDictData> iPage, SysDictDataListViewForm viewForm);

    /**
     * 新增字典数据
     *
     * @param viewForm 字典数据
     * @return 结果
     */
    public ResponseResult insertSysDictData(SysDictDataInsertViewForm viewForm);

    /**
     * 修改字典数据
     *
     * @param viewForm 字典数据
     * @return 结果
     */
    public ResponseResult updateSysDictData(SysDictDataUpdateViewForm viewForm);

    /**
     * 批量删除字典数据
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    public ResponseResult deleteSysDictDataByIds(String[] dictCodes);


    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType
     * @return
     */
    List<SysDictData> selectDictDataByType(String dictType);
}
