package com.ls.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ls.system.entity.common.PreventionCommonEntity;
import lombok.Data;

import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author lsbdp
 */
@Data
public class SysUser extends PreventionCommonEntity {
    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;


    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 头像
     */
    private String imgPath;


    /**
     * 部门
     */
    @TableField(exist = false)
    private SysDept sysDept;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<String> roles;


}
