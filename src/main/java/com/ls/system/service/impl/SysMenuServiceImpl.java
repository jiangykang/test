package com.ls.system.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysRole;
import com.ls.system.entity.common.SysResourceTree;
import com.ls.system.viewform.sysmenu.SysMenuEnableViewForm;
import com.ls.system.viewform.sysrole.SysRoleEnableViewForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.entity.SysMenu;
import com.ls.system.entity.common.TreeSelect;
import com.ls.system.mapper.SysMenuMapper;
import com.ls.system.mapstruct.SysMenuMapStruct;
import com.ls.system.service.SysMenuService;
import com.ls.system.utils.UserInfoUtils;
import com.ls.system.viewform.sysmenu.SysMenuInsertViewForm;
import com.ls.system.viewform.sysmenu.SysMenuListViewForm;
import com.ls.system.viewform.sysmenu.SysMenuUpdateViewForm;
import com.ls.system.vo.ContextUserInfo;
import com.ls.system.vo.MetaVo;
import com.ls.system.vo.RouterVo;
import com.ls.system.vo.SysMenuVO;

import static com.ls.system.constant.Constants.ENABLE;

/**
 * 菜单Service业务层处理
 *
 * @author lsbdp
 * @date 2020-03-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 功能描述: <br>登录成功后根据登录人获取菜单详细信息
     *
     * @param
     * @return java.util.List<com.ls.system.vo.SysMenuVO>
     * @Author: Min.Hu
     * @Date: 2020/3/16 11:00
     */
    @Override
    public List<RouterVo> getLoginUserMenu() {
        //获取当前登录人
        ContextUserInfo userInfo = UserInfoUtils.getLoginUser();
        //根据用户id 获取所有菜单
        List<SysMenuVO> menus = null;

        if (userInfo.getIsAdmin()) {
            menus = sysMenuMapper.selectAllMenu();
        } else {
            menus = sysMenuMapper.selectMenuTreeByUserId(userInfo.getId());
        }
        //构建树
        List<SysMenuVO> list = getChildPerms(menus, "0");
        return buildMenus(list);
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<SysMenuVO> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuVO menu : menus) {
            RouterVo router = new RouterVo();
            router.setName(StringUtils.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<SysMenuVO> cMenus = menu.getChildren();
            if (cMenus != null) {
                if ("M".equals(menu.getMenuType())) {
                    router.setAlwaysShow(true);
                    router.setRedirect("noRedirect");
                    router.setChildren(buildMenus(cMenus));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuVO menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if ("0".equals(menu.getParentId()) && "0".equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }

    /**
     * 查询系统菜单列表
     *
     * @param viewForm 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenuVO> selectMenuList(SysMenuListViewForm viewForm) {
        List<SysMenuVO> menuList = sysMenuMapper.selectMenuList(viewForm);
        return menuList;
    }


    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<String> selectMenuIdListByRoleId(String roleId) {
        return sysMenuMapper.selectMenuIdListByRoleId(roleId);
    }


    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenuVO> menus) {
        List<SysMenuVO> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 是否存在菜单子节点
     *
     * @param id 菜单ID
     * @return 结果 true：存在子节点   false： 不存在子节点
     */
    public Boolean hasChildByMenuId(String id) {
        int result = sysMenuMapper.hasChildByMenuId(id);
        return result > 0 ? true : false;
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    public Boolean checkMenuExistRole(String menuId) {
        int result = sysMenuMapper.checkMenuExistRole(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 新增保存菜单信息
     *
     * @param viewForm 菜单信息
     * @return 结果
     */
    @Override
    public Integer insertMenu(SysMenuInsertViewForm viewForm) {
        //判断菜单名称在当前父类下是否存在
        if (checkMenuNameUnique(null, viewForm.getMenuName(), viewForm.getParentId())) {
            throw new RuntimeException("新增菜单'" + viewForm.getMenuName() + "'失败，菜单名称已存在");
        }
        SysMenu sysMenu = SysMenuMapStruct.INSTANCE.insertViewFormToSysMenu(viewForm);
        sysMenu.preInsert();
        return sysMenuMapper.insert(sysMenu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param viewForm 菜单信息
     * @return 结果
     */
    @Override
    public Integer updateMenu(SysMenuUpdateViewForm viewForm) {
        if (checkMenuNameUnique(viewForm.getId(), viewForm.getMenuName(), viewForm.getParentId())) {
            throw new RuntimeException("修改菜单'" + viewForm.getMenuName() + "'失败，菜单名称已存在");
        }
        SysMenu sysMenu = SysMenuMapStruct.INSTANCE.updateViewFormToSysMenu(viewForm);
        sysMenu.preUpdate();
        return sysMenuMapper.updateById(sysMenu);
    }

    /**
     * 校验菜单名称是否存在
     * 修改时 判断是否是原来的名称
     *
     * @param id
     * @param menuName
     * @param parentId
     * @return 结果  true：存在     false：不存在
     */
    public Boolean checkMenuNameUnique(String id, String menuName, String parentId) {
        SysMenuVO info = sysMenuMapper.checkMenuNameUnique(menuName, parentId);
        if (ObjectUtils.isNotEmpty(info)) {
            if (!info.getMenuId().equals(id)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除 菜单
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteMenuById(String id) {
        if (hasChildByMenuId(id)) {
            throw new RuntimeException("存在子菜单,不允许删除");
        }
        if (checkMenuExistRole(id)) {
            throw new RuntimeException("菜单已分配,不允许删除");
        }

        return sysMenuMapper.deleteById(id);
    }
/**
 * 功能描述: <br>
 * 启用停用
 *
 * @return com.ls.common.constant.ResponseResult
 * @Author: Min.Hu
 * @Date: 2020/3/24 9:25
 */
    @Override
    public ResponseResult enable(SysMenuEnableViewForm viewForm) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(viewForm.getId());
        sysMenu.preUpdate();
        sysMenu.setEnableFlag(viewForm.getEnableFlag());
        int count = sysMenuMapper.updateById(sysMenu);
        String msg = "";
        if (ENABLE.equals(viewForm.getEnableFlag())) {
            msg += "启用";
        } else {
            msg += "停用";
        }
        if (count > 0) {

            return ResponseResult.successMsg(msg + "成功");
        } else {
            return ResponseResult.failure(msg + "失败");
        }
    }


    @Override
    public List<SysResourceTree> getLoginUserMenuVue() {
        //获取当前登录人
        ContextUserInfo userInfo = UserInfoUtils.getLoginUser();
        //根据用户id 获取所有菜单
        List<SysMenuVO> menus = null;

        if (userInfo.getIsAdmin()) {
            menus = sysMenuMapper.selectAllMenu();
        } else {
            menus = sysMenuMapper.selectMenuTreeByUserId(userInfo.getId());
        }
        List<SysResourceTree> treeList = new ArrayList<>();
        menus.forEach(e -> {
            SysResourceTree tree= new SysResourceTree();

            tree.setLabel(e.getMenuName());
            tree.setPath(e.getPath());
            tree.setComponent(e.getComponent());
            tree.setIcon(e.getIcon());
            tree.setIsMenu(e.getMenuType());
            tree.setIsCheck(e.getIsFrame().toString());
            tree.setMenuId(e.getMenuId());
            tree.setParentMenuId(e.getParentId());
            treeList.add(tree);
        });
        //创建树根
        SysResourceTree pSysTree = new SysResourceTree();
        pSysTree.setMenuId("0");
        pSysTree.setParentMenuId("-1");
        pSysTree.setLabel("系统权限");
        createTree(pSysTree, treeList);
        return pSysTree.getChildren();
    }

    // 递归创建资源授权树
    private void createTree(SysResourceTree parentNode, List<SysResourceTree> list) {
        List<SysResourceTree> clist = new ArrayList<>();
        for (SysResourceTree tree : list) {
            if (parentNode.getMenuId().equals(tree.getParentMenuId())) {
                clist.add(tree);
            }
        }
        // 孩子获取下一级孩子
        if (clist.size() > 0) {
            parentNode.setChildren(clist);
            for (SysResourceTree ctree : clist) {
                createTree(ctree, list);// 递归
            }
        }
    }
    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuVO> getChildPerms(List<SysMenuVO> list, String parentId) {
        List<SysMenuVO> returnList = new ArrayList<SysMenuVO>();
        for (Iterator<SysMenuVO> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuVO t = (SysMenuVO) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(parentId)) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<SysMenuVO> buildMenuTree(List<SysMenuVO> menus) {
        List<SysMenuVO> returnList = new ArrayList<SysMenuVO>();
        for (Iterator<SysMenuVO> iterator = menus.iterator(); iterator.hasNext(); ) {
            SysMenuVO t = (SysMenuVO) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if ("0".equals(t.getParentId())) {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        List<SysMenuVO> sortedEmp = returnList.stream().sorted(Comparator.comparing(SysMenuVO::getCustomSort)).collect(Collectors.toList());
        return sortedEmp;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuVO> list, SysMenuVO t) {
        // 得到子节点列表
        List<SysMenuVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVO tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenuVO> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenuVO n = (SysMenuVO) it.next();
                    recursionFn(list, n);
                }
            }
        }
        list.stream().sorted(Comparator.comparing(SysMenuVO::getCustomSort)).collect(Collectors.toList());
    }


    /**
     * 得到子节点列表
     */
    private List<SysMenuVO> getChildList(List<SysMenuVO> list, SysMenuVO t) {
        List<SysMenuVO> tlist = new ArrayList<SysMenuVO>();
        Iterator<SysMenuVO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuVO n = (SysMenuVO) it.next();
            if (n.getParentId().equals(t.getMenuId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVO> list, SysMenuVO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
