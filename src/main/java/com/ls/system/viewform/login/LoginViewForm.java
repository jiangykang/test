package com.ls.system.viewform.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 16:36
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
@ApiModel(value = "登录参数")
public class LoginViewForm {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String code;
    @NotBlank(message = "参数不能为空")
    @ApiModelProperty(value = "uuid")
    private String uuid;

}
