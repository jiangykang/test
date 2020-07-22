package com.ls.system.viewform.sysdept;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

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
@ApiModel("部门对象查询参数")
public class SysDeptListViewForm {

    /**
     * 部门名
     */
    @ApiModelProperty(value = "部门名")
    private String deptName;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private String parentId;

    /**
     * 祖父id
     */
    @ApiModelProperty(value = "祖父id")
    private String parentIds;

    /**
     * 部门等级
     */
    @ApiModelProperty(value = "部门等级")
    private Integer grade;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private String enableFlag;

    private String dataScope;

}
