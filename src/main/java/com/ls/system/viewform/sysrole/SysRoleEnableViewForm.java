package com.ls.system.viewform.sysrole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 角色对象  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-12
 */
@Data
@ApiModel("角色对象修改参数")
public class SysRoleEnableViewForm {


    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
    /**
     * 有效
     */
    @ApiModelProperty(value = "有效状态")
    @NotNull(message = "有效状态不能为空")
    private String enableFlag;

}
