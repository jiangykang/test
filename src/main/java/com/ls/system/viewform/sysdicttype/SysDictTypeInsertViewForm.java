package com.ls.system.viewform.sysdicttype;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 字典类型对象 sys_dict_type  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Data
@ApiModel("字典类型对象添加参数")
public class SysDictTypeInsertViewForm {
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    private String dictName;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer customSort;
    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "启用状态（1正常 0停用）")
    @NotNull(message = "启用状态不能为空")
    private String enableFlag;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}