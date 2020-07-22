package com.ls.system.viewform.sysdicttype;

/**
 * @Author: Min.Hu
 * Time: 2020/4/3 11:08
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

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
@ApiModel("字典类型对象修改参数")
public class SysDictTypeUpdateViewForm {

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @ApiModelProperty(value = "状态（1正常 0停用）")
    private String enableFlag;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer customSort;
    ;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
