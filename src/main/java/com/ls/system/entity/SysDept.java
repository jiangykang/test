package com.ls.system.entity;

import com.ls.system.entity.common.PreventionCommonEntity;
import lombok.Data;

/**
 * 部门表 sys_dept
 *
 * @author lsbdp
 */
@Data
public class SysDept extends PreventionCommonEntity {
    /**
     * 组织id
     */
    private String orgId;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门ID
     */
    private String parentId;

    /**
     * 父部门IDs
     */
    private String parentIds;

    /**
     * 等级
     */
    private Integer grade;
}
