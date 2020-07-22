package com.ls.system.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.ls.system.entity.SysRole;
import com.ls.system.viewform.sysrole.SysRoleInsertViewForm;
import com.ls.system.viewform.sysrole.SysRoleUpdateViewForm;

/**
 * 角色ViewForm转化为实体
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @author Min.Hu
 * @date 2020-03-12
 */
@Mapper
public interface SysRoleMapStruct {

    SysRoleMapStruct INSTANCE = Mappers.getMapper(SysRoleMapStruct.class);


    @Mappings({})
    SysRole insertViewFormToSysRole(SysRoleInsertViewForm viewForm);

    @Mappings({})
    SysRole updateViewFormToSysRole(SysRoleUpdateViewForm viewForm);

}
