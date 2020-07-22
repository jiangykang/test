package com.ls.system.entity;

import com.ls.system.entity.common.PreventionCommonEntity;
import lombok.Data;

/**
 * 菜单表 sys_menu
 *
 * @author lsbdp
 */
@Data
public class SysMenu extends PreventionCommonEntity {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 权限标识
     */
    private String permission;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;
    /**
     * 菜单状态（true显示 false隐藏）
     */
    private String visible;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否为外链（ true 是  false否）
     */
    private String isFrame;
}
