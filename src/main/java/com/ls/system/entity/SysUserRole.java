package com.ls.system.entity;

import lombok.Data;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 17:51
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class SysUserRole {

    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
