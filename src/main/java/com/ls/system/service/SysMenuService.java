package com.ls.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysMenu;
import com.ls.system.entity.common.SysResourceTree;
import com.ls.system.entity.common.TreeSelect;
import com.ls.system.viewform.sysmenu.SysMenuEnableViewForm;
import com.ls.system.viewform.sysmenu.SysMenuInsertViewForm;
import com.ls.system.viewform.sysmenu.SysMenuListViewForm;
import com.ls.system.viewform.sysmenu.SysMenuUpdateViewForm;
import com.ls.system.vo.RouterVo;
import com.ls.system.vo.SysMenuVO;

/**
 * 菜单Service接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author lsbdp
 * @date 2020-03-12
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 功能描述: <br>登录成功后根据登录人获取菜单详细信息
     *
     * @param
     * @return java.util.List<com.ls.system.vo.SysMenuVO>
     * @Author: Min.Hu
     * @Date: 2020/3/13 16:43
     */
    List<RouterVo> getLoginUserMenu();


    /**
     * 查询系统菜单列表
     *
     * @param viewForm 菜单信息
     * @return 菜单列表
     */
    public List<SysMenuVO> selectMenuList(SysMenuListViewForm viewForm);



    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    public List<String> selectMenuIdListByRoleId(String roleId);


    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<SysMenuVO> buildMenuTree(List<SysMenuVO> menus);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenuVO> menus);


    /**
     * 新增保存菜单信息
     *
     * @param viewForm 菜单信息
     * @return 结果
     */
    public Integer insertMenu(SysMenuInsertViewForm viewForm);

    /**
     * 修改保存菜单信息
     *
     * @param viewForm 菜单信息
     * @return 结果
     */
    public Integer updateMenu(SysMenuUpdateViewForm viewForm);

    /**
     * @param id
     * @return
     */
    Integer deleteMenuById(String id);

    /**
     * 启用停用
     *
     * @param viewForm
     * @return
     */
    ResponseResult enable(SysMenuEnableViewForm viewForm);

    /**
     * 登录成功后根据登录人获取菜单详细信息vue
     *
     * @return
     */
    List<SysResourceTree> getLoginUserMenuVue();
}
