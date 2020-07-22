package com.ls.system.entity;

import lombok.Data;

/**
 * @Author: Min.Hu
 * Time: 2020/4/14 9:03
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class SysRoleDept {
    private String id;
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 部门ID
     */
    private String deptId;
}
