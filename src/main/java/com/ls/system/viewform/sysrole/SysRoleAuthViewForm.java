package com.ls.system.viewform.sysrole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel("数据权限修改参数")
public class SysRoleAuthViewForm {

    /**
     * 数据权限
     */
    @ApiModelProperty(value = "数据权限")
    @NotBlank(message = "数据权限不能为空")
    private String dataScope;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门")
    private List<String> deptIds;

}
