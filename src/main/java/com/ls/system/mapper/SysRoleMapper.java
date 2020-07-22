package com.ls.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ls.system.entity.SysRole;
import com.ls.system.viewform.sysrole.SysRoleListViewForm;
import com.ls.system.vo.SysRoleVO;

/**
 * @Author: Min.Hu
 * Time: 2020/3/10 17:01
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 功能描述: <br> 根据 userId 查询所有的角色标识
     *
     * @param id
     * @return java.util.Set<java.lang.String>
     * @Author: Min.Hu
     * @Date: 2020/3/11 9:33
     */
    Set<String> selectAllRoleKeyByUserId(String id);

    /**
     * 功能描述: <br>据 userId 查询所有的角色
     *
     * @param id
     * @return java.util.List<com.ls.system.entity.SysRole>
     * @Author: Min.Hu
     * @Date: 2020/3/12 14:08
     */
    Set<SysRoleVO> selectListByUserId(@Param("userId") String userId);

    /**
     * 查询角色列表
     *
     * @param sysRole 角色
     * @return 角色集合
     */
    public IPage<SysRole> selectSysRoleList(IPage<SysRole> iPage, SysRoleListViewForm viewForm);

    /**
     * 获取角色选择框列表
     *
     * @return
     */
    List<SysRoleVO> selectRoleAll();


    /**
     * 根据 角色名称查询
     *
     * @param roleName
     * @return
     */
    SysRole selectListByRoleName(String roleName);

    /**
     * 根据 角色名称查询
     *
     * @param roleName
     * @return
     */
    SysRole selectListByRoleKey(String roleKey);
}
