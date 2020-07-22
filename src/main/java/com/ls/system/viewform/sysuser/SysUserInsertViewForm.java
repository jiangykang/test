package com.ls.system.viewform.sysuser;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 用户对象 sys_user  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-12
 */
@Data
@ApiModel("用户对象添加参数")
public class SysUserInsertViewForm {

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private Long phone;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    private String idCard;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    @NotBlank(message = "部门id")
    private String deptId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    @NotBlank(message = "工名不能为空")
    private String jobNumber;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 有效
     */
    @ApiModelProperty(value = "有效")
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
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String imgPath;



    @ApiModelProperty(value = "角色", required = true)
    @NotEmpty(message = "角色不能为空")
    private List<String> roles;

}