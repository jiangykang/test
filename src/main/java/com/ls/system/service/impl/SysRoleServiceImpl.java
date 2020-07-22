package com.ls.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysRole;
import com.ls.system.entity.SysRoleDept;
import com.ls.system.entity.SysRoleMenu;
import com.ls.system.mapper.SysRoleDeptMapper;
import com.ls.system.mapper.SysRoleMapper;
import com.ls.system.mapper.SysRoleMenuMapper;
import com.ls.system.mapstruct.SysRoleMapStruct;
import com.ls.system.service.SysRoleService;
import com.ls.system.utils.GenerateIdUtil;
import com.ls.system.viewform.sysrole.*;
import com.ls.system.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ls.system.constant.Constants.ENABLE;

/**
 * 角色Service业务层处理
 *
 * @author Min.Hu
 * @date 2020-03-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;

    private final String ADMIN = "admin";

    /**
     * 查询角色列表
     *
     * @param viewForm 角色
     * @return 角色
     */
    @Override
    public IPage<SysRole> selectSysRoleList(IPage<SysRole> iPage, SysRoleListViewForm viewForm) {
        return sysRoleMapper.selectSysRoleList(iPage, viewForm);
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(SysRoleAuthViewForm viewForm) {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDept> list = new ArrayList<>();
        for (String deptId : viewForm.getDeptIds()) {
            SysRoleDept rd = new SysRoleDept();
            rd.setId(GenerateIdUtil.generateId());
            rd.setRoleId(viewForm.getId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0) {
            rows = sysRoleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * 新增角色
     *
     * @param viewForm 角色
     * @return 结果
     */
    @Override
    public ResponseResult insertSysRole(SysRoleInsertViewForm viewForm) {
        //检测是否操作管理员角色
        checkRoleAllowed(viewForm.getRoleKey());
        if (checkRoleNameUnique(null, viewForm.getRoleName())) {
            throw new RuntimeException("新增角色'" + viewForm.getRoleName() + "'失败，角色名称已存在");
        }
        if (checkRoleKeyUnique(null, viewForm.getRoleKey())) {
            throw new RuntimeException("新增角色'" + viewForm.getRoleName() + "'失败，角色名称已存在");
        }
        SysRole sysRole = SysRoleMapStruct.INSTANCE.insertViewFormToSysRole(viewForm);
        //以下内容会写成公共方法
        sysRole.preInsert();
        sysRoleMapper.insert(sysRole);
        if (viewForm.getMenuIds() != null && !viewForm.getMenuIds().isEmpty()) {
            // 新增角色菜单关系
            List<SysRoleMenu> list = new ArrayList<>(10);
            for (String menuId : viewForm.getMenuIds()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(GenerateIdUtil.generateId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(sysRole.getId());
                list.add(sysRoleMenu);
            }
            sysRoleMenuMapper.batchRoleMenu(list);
        }
        return ResponseResult.success("新增成功");
    }


    /**
     * 修改角色
     *
     * @param viewForm 角色
     * @return 结果
     */
    @Override
    public ResponseResult updateSysRole(SysRoleUpdateViewForm viewForm) {
        //检测是否操作管理员角色
        checkRoleAllowed(viewForm.getRoleKey());

        if (checkRoleNameUnique(viewForm.getId(), viewForm.getRoleName())) {
            throw new RuntimeException("修改角色'" + viewForm.getRoleName() + "'失败，角色名称已存在");
        }
        if (checkRoleKeyUnique(viewForm.getId(), viewForm.getRoleKey())) {
            throw new RuntimeException("修改角色'" + viewForm.getRoleName() + "'失败，角色值已存在");
        }
        SysRole sysRole = SysRoleMapStruct.INSTANCE.updateViewFormToSysRole(viewForm);
        sysRole.preUpdate();
        sysRoleMapper.updateById(sysRole);
        //删除所有该角色的菜单
        sysRoleMenuMapper.deleteRoleMenuByRoleId(sysRole.getId());
        if (viewForm.getMenuIds() != null && !viewForm.getMenuIds().isEmpty()) {
            // 新增角色菜单关系
            List<SysRoleMenu> list = new ArrayList<>();
            for (String menuId : viewForm.getMenuIds()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(GenerateIdUtil.generateId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(sysRole.getId());
                list.add(sysRoleMenu);
            }
            sysRoleMenuMapper.batchRoleMenu(list);
        }
        return ResponseResult.success("修改成功");
    }

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的角色ID
     * @return 结果
     */
    @Override
    public ResponseResult deleteSysRoleByIds(String[] ids) {
        List<String> list = Arrays.asList(ids);
        for (SysRole sysRole : sysRoleMapper.selectBatchIds(list)) {
            //检测是否操作管理员角色
            checkRoleAllowed(sysRole.getRoleKey());
            if (sysRoleMenuMapper.countUserRoleByRoleId(sysRole.getId()) > 0) {
                throw new RuntimeException(String.format("%1$s已分配,不能删除", sysRole.getRoleName()));
            }
            //删除所有该角色的菜单
            sysRoleMenuMapper.deleteRoleMenuByRoleId(sysRole.getId());
        }
        //此处是真实删除  后期根据业务 可自由定义成逻辑删除
        sysRoleMapper.deleteBatchIds(list);
        return ResponseResult.success("删除成功");
    }

    /**
     * 获取角色选择框列表
     *
     * @return
     */
    @Override
    public List<SysRoleVO> selectRoleAll() {
        return sysRoleMapper.selectRoleAll();
    }

    /**
     * 校验角色是否允许操作
     *
     * @param roleKey
     * @return true:是管理员角色  false：不是管理员角色
     */
    public void checkRoleAllowed(String roleKey) {
        if (ADMIN.equals(roleKey)) {
            throw new RuntimeException("不允许操作超级管理员角色");
        }
    }

    /**
     * 启用停用
     *
     * @param viewForm
     * @return
     */
    @Override
    public ResponseResult enable(SysRoleEnableViewForm viewForm) {
        SysRole sysRole = new SysRole();
        sysRole.setId(viewForm.getId());
        sysRole.setEnableFlag(viewForm.getEnableFlag());
        sysRole.preUpdate();
        int count = sysRoleMapper.updateById(sysRole);
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
    public ResponseResult authDataScope(SysRoleAuthViewForm viewForm) {
        SysRole sysRole = sysRoleMapper.selectById(viewForm.getId());
        checkRoleAllowed(sysRole.getRoleKey());
        // 修改角色信息
        SysRole role = new SysRole();
        role.setId(viewForm.getId());
        role.setDataScope(viewForm.getDataScope());
        role.preUpdate();
        sysRoleMapper.updateById(role);
        // 删除角色与部门关联
        sysRoleDeptMapper.deleteRoleDeptByRoleId(role.getId());
        // 新增角色和部门信息（数据权限）
        insertRoleDept(viewForm);
        return ResponseResult.successMsg("修改成功");
    }

    /**
     * 校验角色名称是否存在
     *
     * @param id
     * @param roleName
     * @return 结果  true：存在     false：不存在
     */
    public Boolean checkRoleNameUnique(String id, String roleName) {
        SysRole sysRole = sysRoleMapper.selectListByRoleName(roleName);
        if (ObjectUtils.isNotEmpty(sysRole)) {
            if (sysRole.getId().equals(id)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 校验角色值是否存在
     *
     * @param id
     * @param roleKey
     * @return 结果  true：存在     false：不存在
     */
    public Boolean checkRoleKeyUnique(String id, String roleKey) {
        SysRole sysRole = sysRoleMapper.selectListByRoleKey(roleKey);
        if (ObjectUtils.isNotEmpty(sysRole)) {
            if (sysRole.getId().equals(id)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


}


