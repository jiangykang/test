package com.ls.system.viewform.sysrole;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 角色对象 sys_role  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-12
 */
@Data
@ApiModel("角色对象修改参数")
public class SysRoleUpdateViewForm {

    /**
     * 角色值
     */
    @ApiModelProperty(value = "角色值")
    private String roleKey;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 数据权限
     */
    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
    /**
     * 有效
     */
    @ApiModelProperty(value = "启用")
    private String enableFlag;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer customSort;

    /**
     * 菜单
     */
    @ApiModelProperty(value = "菜单")
    private List<String> menuIds;

}
