package com.ls.system.viewform.sysdictdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel("字典数据对象查询参数")
public class SysDictDataListViewForm {


    /**
     * 字典标签
     */
    @ApiModelProperty(value = "字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
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
     * 状态（1正常 0停用）
     */
    @ApiModelProperty(value = "启用状态（1正常 0停用）")
    private String enableFlag;
}
