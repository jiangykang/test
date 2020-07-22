package com.ls.system.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysRole;
import com.ls.system.viewform.sysrole.*;
import com.ls.system.vo.SysRoleVO;

/**
 * 角色Service接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 * @author Min.Hu
 * @date 2020-03-12
 */
public interface SysRoleService extends IService<SysRole>
{

    /**
     * 查询角色列表
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    public IPage<SysRole> selectSysRoleList(IPage<SysRole> iPage, SysRoleListViewForm viewForm);

    /**
     * 新增角色
     *
     * @param viewForm 角色
     * @return 结果
     */
    public ResponseResult insertSysRole(SysRoleInsertViewForm viewForm);

    /**
     * 修改角色
     *
     * @param viewForm 角色
     * @return 结果
     */
    public ResponseResult updateSysRole(SysRoleUpdateViewForm viewForm);

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的角色ID
     * @return 结果
     */
    public ResponseResult deleteSysRoleByIds(String[] ids);


    /**
     * 获取角色选择框列表
     *
     * @return
     */
    List<SysRoleVO> selectRoleAll();

    /**
     * 启用停用
     *
     * @param viewForm
     * @return
     */
    ResponseResult enable(SysRoleEnableViewForm viewForm);

    /**
     * 修改保存数据权限
     *
     * @param viewForm
     * @return
     */
    ResponseResult authDataScope(SysRoleAuthViewForm viewForm);
}
