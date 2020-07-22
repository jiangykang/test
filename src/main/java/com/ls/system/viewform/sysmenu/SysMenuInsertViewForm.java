package com.ls.system.viewform.sysmenu;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel("菜单对象添加参数")
public class SysMenuInsertViewForm {

    /**
     * 菜单名
     */
    @ApiModelProperty(value = "菜单名", required = true)
    @NotBlank(message = "菜单名不能为空")
    private String menuName;

    /**
     * 父菜单id
     */
    @ApiModelProperty(value = "父菜单id", required = true)
    @NotBlank(message = "父菜单id不能为空")
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
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;
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
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    /**
     * 是否为外链（ true 是  false否）
     */
    @ApiModelProperty(value = "是否为外链（ true 是  false否）", example = "false")
    private String isFrame;
    /**
     * 自定义排序
     */
    @ApiModelProperty(value = "自定义排序")
    private Integer customSort;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用 true：启用 false：不启用 ")
    private String enableFlag;
    /**
     * 備注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
