package com.ls.system.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDept;
import com.ls.system.entity.common.TreeSelect;
import com.ls.system.viewform.sysdept.SysDeptInsertViewForm;
import com.ls.system.viewform.sysdept.SysDeptListViewForm;
import com.ls.system.viewform.sysdept.SysDeptUpdateViewForm;
import com.ls.system.vo.SysDeptVO;

/**
 * 部门Service接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author MIn.Hu
 * @date 2020-03-16
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询部门列表
     *
     * @param sysDept 部门
     * @return 部门集合
     */
    public IPage<SysDept> selectSysDeptList(SysDept sysDept, IPage<SysDept> iPage);

    /**
     * 新增部门
     *
     * @param sysDept 部门
     * @return 结果
     */
    public ResponseResult insertSysDept(SysDeptInsertViewForm viewForm);

    /**
     * 修改部门
     *
     * @param sysDept 部门
     * @return 结果
     */
    public ResponseResult updateSysDept(SysDeptUpdateViewForm viewForm);


    /**
     * 删除部门信息
     *
     * @param id 部门ID
     * @return 结果
     */
    public Integer deleteSysDeptById(String id);

    /**
     * 获取部门下拉树列表
     *
     * @param sysDept
     * @return
     */
    List<TreeSelect> selectDeptList(SysDept sysDept);


    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    List<Integer> selectDeptListByRoleId(String roleId);


    /**
     * 根据父id获取部门
     *
     * @param  sysDept
     * @return
     */
    List<SysDeptVO> getListByParentId(SysDept sysDept);
}
