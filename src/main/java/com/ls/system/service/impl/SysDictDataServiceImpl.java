package com.ls.system.service.impl;

import java.util.List;

import com.ls.system.config.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ls.system.mapper.SysDictDataMapper;
import com.ls.system.entity.SysDictData;
import com.ls.system.viewform.sysdictdata.*;
import com.ls.system.service.SysDictDataService;
import com.ls.system.mapstruct.SysDictDataMapStruct;

import java.util.Arrays;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典数据Service业务层处理
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询字典数据列表
     *
     * @param sysDictData 字典数据
     * @return 字典数据
     */
    @Override
    public IPage<SysDictData> selectSysDictDataList(IPage<SysDictData> iPage, SysDictDataListViewForm viewForm) {
        return sysDictDataMapper.selectSysDictDataList(iPage, viewForm);
    }


    /**
     * 新增字典数据
     *
     * @param sysDictData 字典数据
     * @return 结果
     */
    @Override
    public ResponseResult insertSysDictData(SysDictDataInsertViewForm viewForm) {

        SysDictData sysDictData = SysDictDataMapStruct.INSTANCE.insertViewFormToSysDictData(viewForm);

        //以下内容会写成公共方法
        sysDictData.preInsert();

        sysDictDataMapper.insert(sysDictData);
        return ResponseResult.success("新增成功");
    }

    /**
     * 修改字典数据
     *
     * @param sysDictData 字典数据
     * @return 结果
     */
    @Override
    public ResponseResult updateSysDictData(SysDictDataUpdateViewForm viewForm) {

        SysDictData sysDictData = SysDictDataMapStruct.INSTANCE.updateViewFormToSysDictData(viewForm);
        sysDictData.preUpdate();

        sysDictDataMapper.updateById(sysDictData);
        return ResponseResult.success("修改成功");
    }

    /**
     * 批量删除字典数据
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 结果
     */
    @Override
    public ResponseResult deleteSysDictDataByIds(String[] dictCodes) {

        List<String> list = Arrays.asList(dictCodes);
        //此处是真实删除  后期根据业务 可自由定义成逻辑删除
        sysDictDataMapper.deleteBatchIds(list);
        return ResponseResult.success("删除成功");
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType
     * @return
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return sysDictDataMapper.selectDictDataByType(dictType);
    }
}
