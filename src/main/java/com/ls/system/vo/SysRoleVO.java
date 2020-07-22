package com.ls.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 14:10
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class SysRoleVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色值
     */
    private String roleKey;

    /**
     * 数据权限
     */
    private String dataScope;
}
