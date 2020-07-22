package com.ls.system.viewform.sysdept;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门对象 sys_dept  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-16
 */
@Data
@ApiModel("部门对象添加参数")
public class SysDeptInsertViewForm {

    /**
     * 部门名
     */
    @ApiModelProperty(value = "部门名", required = true)
    @NotBlank(message = "部门名不能为空")
    private String deptName;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", required = true)
    @NotBlank(message = "父id不能为空")
    private String parentId;

    /**
     * 部门等级
     */
    @ApiModelProperty(value = "部门等级")
    private Integer grade;

    /**
     * 有效
     */
    @ApiModelProperty(value = "有效", required = true)
    @NotNull(message = "有效不能为空")
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
}
