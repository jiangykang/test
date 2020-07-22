package com.ls.system.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 14:10
 * <p>
 * When I wrote this; only God and I understood what I was doing
 * Now; God only knows
 * Good Luck
 **/
@Data
public class SysMenuVO {

    private String menuId;
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
    /**
     * 自定义排序
     */
    private Integer customSort;
    /**
     * 備注
     */
    private String remark;

    private List<SysMenuVO> children;
}
