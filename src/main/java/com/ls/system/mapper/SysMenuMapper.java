package com.ls.system.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ls.system.entity.SysMenu;
import com.ls.system.viewform.sysmenu.SysMenuListViewForm;
import com.ls.system.vo.SysMenuVO;

/**
 * @Author: Min.Hu
 * Time: 2020/3/10 17:01
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
  /**
   * 功能描述: <br> 根据角色查菜单
   *
   * @param roleId
   * @return java.util.List<java.lang.Integer>
   * @Author: Min.Hu
   * @Date: 2020/3/16 11:05
   */
  List<String> selectMenuIdListByRoleId(String roleId);

  /**
   * 功能描述: <br> 根据userId 获取所有的权限标识
   *
   * @param userId
   * @return java.util.Set<java.lang.String>
   * @Author: Min.Hu
   * @Date: 2020/3/11 9:42
   */
  Set<String> selectAllPermissionByUserId(String userId);

  /**
   * 功能描述: <br> 获取所有菜单
   *
   * @return java.util.List<com.ls.system.vo.SysMenuVO>
   * @Author: Min.Hu
   * @Date: 2020/3/12 14:45
   */
  List<SysMenuVO> selectAllMenu();

  /**
   * 功能描述: <br> 检查该菜单在父菜单下是否存在
   *
   * @param menuName
   * @param parentId
   * @return com.ls.system.vo.SysMenuVO
   * @Author: Min.Hu
   * @Date: 2020/3/13 16:55
   */
  SysMenuVO checkMenuNameUnique(String menuName, String parentId);

  /**
   * 条件查询 所有菜单
   * 只能查出未删除的and启用的
   *
   * @param viewForm
   * @return
   */
  List<SysMenuVO> selectMenuList(@Param("viewForm") SysMenuListViewForm viewForm);

  /**
   * 功能描述: <br>是否有子菜单 未删除的
   * 未启用的也会查出
   *
   * @param id
   * @return java.lang.Integer
   * @Author: Min.Hu
   * @Date: 2020/3/16 10:33
   */
  Integer hasChildByMenuId(String id);

  /**
   * 功能描述: <br> 检查菜单是否已分配
   *
   * @param menuId
   * @return java.lang.Integer
   * @Author: Min.Hu
   * @Date: 2020/3/16 10:32
   */
  Integer checkMenuExistRole(String menuId);

  /**
   * 根据userId 查询菜单权限
   *
   * @param userId
   * @return
   */
  List<SysMenuVO> selectMenuTreeByUserId(String userId);

}
