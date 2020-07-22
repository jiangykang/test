package com.ls.system.viewform.sysrole;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.util.Map;

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
@ApiModel("角色对象查询参数")
public class SysRoleListViewForm {

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
     * 启用状态（ true 是  false否）
     */
    @ApiModelProperty(value = "启用状态（ true 是  false否）")
    private String enableFlag;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private Map<String, Object> params;
}
