package com.ls.system.viewform.sysmenu;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 菜单对象 sys_menu  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-12
 */
@Data
@ApiModel("菜单对象查询参数")
public class SysMenuListViewForm {

    /**
     * 菜单名
     */
    @ApiModelProperty(value = "菜单名")
    private String menuName;

    /**
     * 父菜单id
     */
    @ApiModelProperty(value = "父菜单id")
    private String parentId;

    /**
     * 父菜单名
     */
    @ApiModelProperty(value = "父菜单名")
    private String parentName;

    /**
     * 组建路径
     */
    @ApiModelProperty(value = "组建路径")
    private String component;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    /**
     * 菜单状态（true显示 false隐藏）
     */
    @ApiModelProperty(value = "菜单状态（true显示 false隐藏）")
    private String visible;

    /**
     * 是否为外链（ true 是  false否）
     */
    @ApiModelProperty(value = "是否为外链（ true 是  false否）")
    private String isFrame;

    /**
     * 启用状态（ true 是  false否）
     */
    @ApiModelProperty(value = "启用状态（ true 是  false否）")
    private String enableFlag;
}
