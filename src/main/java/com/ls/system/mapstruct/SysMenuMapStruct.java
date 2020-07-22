package com.ls.system.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.ls.system.entity.SysMenu;
import com.ls.system.viewform.sysmenu.SysMenuInsertViewForm;
import com.ls.system.viewform.sysmenu.SysMenuUpdateViewForm;

/**
 * 菜单ViewForm转化为实体
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @author lsbdp
 * @date 2020-03-12
 */
@Mapper
public interface SysMenuMapStruct {

    SysMenuMapStruct INSTANCE = Mappers.getMapper(SysMenuMapStruct.class);


    @Mappings({})
    SysMenu insertViewFormToSysMenu(SysMenuInsertViewForm viewForm);

    @Mappings({})
    SysMenu updateViewFormToSysMenu(SysMenuUpdateViewForm viewForm);

}
