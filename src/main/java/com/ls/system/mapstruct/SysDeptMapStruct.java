package com.ls.system.mapstruct;

import com.ls.system.viewform.sysdept.SysDeptListViewForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.ls.system.entity.SysDept;
import com.ls.system.viewform.sysdept.SysDeptInsertViewForm;
import com.ls.system.viewform.sysdept.SysDeptUpdateViewForm;

/**
 * 部门ViewForm转化为实体
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @author MIn.Hu
 * @date 2020-03-16
 */
@Mapper
public interface SysDeptMapStruct {

    SysDeptMapStruct INSTANCE = Mappers.getMapper(SysDeptMapStruct.class);

    /**
     * 新增参数转化为实体
     *
     * @param viewForm
     * @return
     */
    @Mappings({})
    SysDept insertViewFormToSysDept(SysDeptInsertViewForm viewForm);

    /**
     * 修改参数转化为实体
     *
     * @param viewForm
     * @return
     */
    @Mappings({})
    SysDept updateViewFormToSysDept(SysDeptUpdateViewForm viewForm);

    /**
     * 查询参数转化为实体
     *
     * @param viewForm
     * @return
     */
    @Mappings({})
    SysDept listViewFormToSysDept(SysDeptListViewForm viewForm);

}
