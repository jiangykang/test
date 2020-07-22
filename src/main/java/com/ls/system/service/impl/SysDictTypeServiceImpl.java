package com.ls.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDictType;
import com.ls.system.mapper.SysDictTypeMapper;
import com.ls.system.service.SysDictTypeService;
import com.ls.system.viewform.sysdicttype.SysDictTypeInsertViewForm;
import com.ls.system.viewform.sysdicttype.SysDictTypeListViewForm;
import com.ls.system.viewform.sysdicttype.SysDictTypeUpdateViewForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 字典类型Service业务层处理
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    /**
     * 查询字典类型列表
     *
     * @param sysDictType 字典类型
     * @return 字典类型
     */
    @Override
    public IPage<SysDictType> selectSysDictTypeList(IPage<SysDictType> iPage, SysDictTypeListViewForm viewForm) {
        return sysDictTypeMapper.selectSysDictTypeList(iPage, viewForm);
    }


    /**
     * 新增字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    @Override
    public ResponseResult insertSysDictType(SysDictTypeInsertViewForm viewForm) {
        if (checkDictTypeUnique(null, viewForm.getDictType())) {
            throw new RuntimeException("新增字典类型'" + viewForm.getDictName() + "'失败，字典类型已存在");
        }
        SysDictType sysDictType = new SysDictType();
        sysDictType.setDictName(viewForm.getDictName());
        sysDictType.setDictType(viewForm.getDictType());
        sysDictType.setEnableFlag(viewForm.getEnableFlag());
        sysDictType.setRemark(viewForm.getRemark());
        //以下内容会写成公共方法
        sysDictType.preInsert();

        sysDictTypeMapper.insert(sysDictType);
        return ResponseResult.success("新增成功");
    }

    /**
     * 修改字典类型
     *
     * @param sysDictType 字典类型
     * @return 结果
     */
    @Override
    public ResponseResult updateSysDictType(SysDictTypeUpdateViewForm viewForm) {
        if (checkDictTypeUnique(viewForm.getId(), viewForm.getDictType())) {
            throw new RuntimeException("修改字典类型'" + viewForm.getDictName() + "'失败，字典类型已存在");
        }
        SysDictType sysDictType = new SysDictType();

        sysDictType.setId(viewForm.getId());
        sysDictType.setDictName(viewForm.getDictName());
        sysDictType.setDictType(viewForm.getDictType());
        sysDictType.setEnableFlag(viewForm.getEnableFlag());
        sysDictType.setCustomSort(viewForm.getCustomSort());
        sysDictType.setRemark(viewForm.getRemark());
        sysDictType.preUpdate();

        sysDictTypeMapper.updateById(sysDictType);
        return ResponseResult.success("修改成功");
    }

    /**
     * 批量删除字典类型
     *
     * @param dictIds 需要删除的字典类型ID
     * @return 结果
     */
    @Override
    public ResponseResult deleteSysDictTypeByIds(String[] dictIds) {

        List<String> list = Arrays.asList(dictIds);
        //此处是真实删除  后期根据业务 可自由定义成逻辑删除
        sysDictTypeMapper.deleteBatchIds(list);
        return ResponseResult.success("删除成功");
    }


    @Override
    public List<SysDictType> selectDictTypeAll() {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setDeleteFlag(false);
        Wrapper wrapper = new QueryWrapper<>(sysDictType);
        return sysDictTypeMapper.selectList(wrapper);
    }

    /**
     * 字典类型 是否存在
     *
     * @param id
     * @param dictType
     * @return
     */
    public Boolean checkDictTypeUnique(String id, String dictType) {
        SysDictType sysDictType = sysDictTypeMapper.checkDictTypeUnique(dictType);
        if (ObjectUtils.isNotEmpty(sysDictType)) {
            if (sysDictType.getId().equals(id)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
