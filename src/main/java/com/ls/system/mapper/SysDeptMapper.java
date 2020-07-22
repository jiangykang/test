package com.ls.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ls.system.entity.SysDept;
import com.ls.system.viewform.sysdept.SysDeptListViewForm;
import com.ls.system.vo.SysDeptVO;

/**
 * 部门Mapper接口
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @author MIn.Hu
 * @date 2020-03-16
 */
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {
    /**
     * 查询部门列表
     *
     * @param entity 部门
     * @param iPage  分页
     * @return 部门集合
     */
    public IPage<SysDept> selectSysDeptList(@Param("entity") SysDept entity, IPage<SysDept> iPage);

    /**
     * 查询部门管理数据
     *
     * @param entity 部门信息
     * @return 部门信息集合
     */
    List<SysDeptVO> selectDeptList(@Param("entity") SysDept entity);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    public List<Integer> selectDeptListByRoleId(String roleId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    SysDept checkDeptNameUnique(String deptName, String parentId);

    /**
     * 根据ID查询所有子部门
     *
     * @param id 部门ID
     * @return 部门列表
     */
    List<SysDept> selectChildrenDeptById(String id);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    void updateDeptChildren(@Param(value = "depts") List<SysDept> depts);

    /**
     * 是否存在子节点
     *
     * @param id 部门ID
     * @return 结果
     */
    Integer hasChildByDeptId(String id);

    /**
     * 查询部门是否存在用户
     *
     * @param id 部门ID
     * @return 结果
     */
    Integer checkDeptExistUser(String id);

    /**
     * 查询所有id
     *
     * @return
     */
    List<String> selectAllId();

    /**
     * 根据父id获取部门
     *
     * @param sysDept
     * @return
     */
    List<SysDeptVO> getListByParentId(@Param("entity") SysDept entity);
}
