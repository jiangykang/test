package com.ls.system.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.ls.system.entity.SysUser;
import com.ls.system.viewform.sysuser.SysUserInsertViewForm;
import com.ls.system.viewform.sysuser.SysUserUpdateViewForm;

/**
 * 用户ViewForm转化为实体
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @author lsbdp
 * @date 2020-03-12
 */
@Mapper
public interface SysUserMapStruct {

    SysUserMapStruct INSTANCE = Mappers.getMapper(SysUserMapStruct.class);


    @Mappings({})
    SysUser insertViewFormToSysUser(SysUserInsertViewForm viewForm);

    @Mappings({})
    SysUser updateViewFormToSysUser(SysUserUpdateViewForm viewForm);

}
