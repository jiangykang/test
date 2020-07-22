package com.ls.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ls.system.entity.SysDictData;
import com.ls.system.viewform.sysdictdata.SysDictDataListViewForm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典数据Mapper接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Repository
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    /**
     * 查询字典数据列表
     *
     * @param iPage    分页
     * @param viewForm 字典数据
     * @return
     */
    IPage<SysDictData> selectSysDictDataList(IPage<SysDictData> iPage, SysDictDataListViewForm viewForm);

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType
     * @return
     */
    List<SysDictData> selectDictDataByType(String dictType);
}
