package com.ls.system.viewform.sysdictdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字典数据对象 sys_dict_data  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-03
 */
@Data
@ApiModel("字典数据对象添加参数")
public class SysDictDataInsertViewForm {
    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签", required = true)
    @NotBlank(message = "字典标签不能为空")
    private String dictLabel;
    /**
     * 字典键值
     */
    @ApiModelProperty(value = "字典键值", required = true)
    @NotBlank(message = "字典键值不能为空")
    private String dictValue;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", required = true)
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
    /**
     * 样式属性（其他样式扩展）
     */
    @ApiModelProperty(value = "样式属性（其他样式扩展）")
    private String cssClass;
    /**
     * 表格回显样式
     */
    @ApiModelProperty(value = "表格回显样式")
    private String listClass;
    /**
     * 是否默认（Y是 N否）
     */
    @ApiModelProperty(value = "是否默认（Y是 N否）")
    private String isDefault;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer customSort;
    /**
     * 状态（1正常 0停用）
     */
    @ApiModelProperty(value = "启用状态（1正常 0停用）")
    @NotBlank(message = "启用状态不能为空")
    private String enableFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
