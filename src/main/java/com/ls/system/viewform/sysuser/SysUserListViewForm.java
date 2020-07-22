package com.ls.system.viewform.sysuser;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * 用户对象 sys_user  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @author lsbdp
 * @date 2020-03-12
 */
@Data
@ApiModel("用户对象查询参数")
public class SysUserListViewForm {

    /**
     * 电话号码
     */
    @ApiModelProperty(value = "电话号码")
    private Long phone;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private String deptId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String jobNumber;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 密码盐
     */
    @ApiModelProperty(value = "密码盐")
    private String salt;
    /**
     * 启用状态（ true 是  false否）
     */
    @ApiModelProperty(value = "启用状态（ true 是  false否）")
    private String enableFlag;
}