package com.ls.system.entity;

import com.ls.system.entity.common.PreventionCommonEntity;
import lombok.Data;

/**
 * 角色表 sys_role
 *
 * @author lsbdp
 */
@Data
public class SysRole extends PreventionCommonEntity {

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
