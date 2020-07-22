package com.ls.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ls.system.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 17:54
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public Integer checkMenuExistRole(Long menuId);

    /**
     * 通过角色ID删除角色和菜单关联
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public Integer deleteRoleMenuByRoleId(String roleId);

    /**
     * 批量新增角色菜单信息
     *
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    public Integer batchRoleMenu(List<SysRoleMenu> roleMenuList);

    Integer countUserRoleByRoleId(String id);
}
