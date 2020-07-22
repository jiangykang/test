package com.ls.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ls.system.entity.SysDictType;
import com.ls.system.entity.SysRole;
import com.ls.system.viewform.sysdicttype.SysDictTypeListViewForm;
import org.springframework.stereotype.Repository;

/**
 * 字典类型Mapper接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Repository
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    /**
     * 查询字典类型列表
     *
     * @param viewForm 字典类型
     * @return 字典类型集合
     */
    IPage<SysDictType> selectSysDictTypeList(IPage<SysDictType> iPage, SysDictTypeListViewForm viewForm);

    /**
     * 字典类型 是否存在
     * @param dictType
     * @return
     */
    SysDictType checkDictTypeUnique(String dictType);
}
