package com.ls.system.service;

import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDictType;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ls.system.viewform.sysdicttype.SysDictTypeInsertViewForm;
import com.ls.system.viewform.sysdicttype.SysDictTypeListViewForm;
import com.ls.system.viewform.sysdicttype.SysDictTypeUpdateViewForm;

/**
 * 字典类型Service接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @Author Min.Hu
 * @date 2020-04-03
 */
public interface SysDictTypeService extends IService<SysDictType>
{

    /**
     * 查询字典类型列表
     *
     * @param sysDictType 字典类型
     * @return 字典类型集合
     */
    public IPage<SysDictType> selectSysDictTypeList(IPage<SysDictType> iPage, SysDictTypeListViewForm viewForm);

    /**
     * 新增字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    public ResponseResult insertSysDictType(SysDictTypeInsertViewForm viewForm);

    /**
     * 修改字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    public ResponseResult updateSysDictType(SysDictTypeUpdateViewForm viewForm);

    /**
     * 批量删除字典类型
     *
     * @param dictIds 需要删除的字典类型ID
     * @return 结果
     */
    public ResponseResult deleteSysDictTypeByIds(String[] dictIds);


    List<SysDictType> selectDictTypeAll();
}
