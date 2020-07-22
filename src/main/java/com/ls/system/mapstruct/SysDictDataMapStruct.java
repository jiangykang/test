package com.ls.system.mapstruct;

import com.ls.system.entity.SysDictData;
import com.ls.system.viewform.sysdictdata.SysDictDataInsertViewForm;
import com.ls.system.viewform.sysdictdata.SysDictDataUpdateViewForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 字典数据ViewForm转化为实体
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Mapper
public interface SysDictDataMapStruct {

    SysDictDataMapStruct INSTANCE = Mappers.getMapper(SysDictDataMapStruct.class);


    @Mappings({})
    SysDictData insertViewFormToSysDictData(SysDictDataInsertViewForm viewForm);

    @Mappings({})
    SysDictData updateViewFormToSysDictData(SysDictDataUpdateViewForm viewForm);

}
