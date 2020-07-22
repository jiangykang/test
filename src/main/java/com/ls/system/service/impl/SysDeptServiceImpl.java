package com.ls.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysDept;
import com.ls.system.entity.common.TreeSelect;
import com.ls.system.interceptor.annotation.DataScope;
import com.ls.system.mapper.SysDeptMapper;
import com.ls.system.mapstruct.SysDeptMapStruct;
import com.ls.system.service.SysDeptService;
import com.ls.system.viewform.sysdept.SysDeptInsertViewForm;
import com.ls.system.viewform.sysdept.SysDeptUpdateViewForm;
import com.ls.system.vo.SysDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ls.system.constant.Constants.ENABLE;
import static com.ls.system.constant.Constants.UN_ENABLE;

/**
 * 部门Service业务层处理
 *
 * @author MIn.Hu
 * @date 2020-03-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询部门列表
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    @Override
    @DataScope(deptAlias = "d")
    public IPage<SysDept> selectSysDeptList(SysDept sysDept, IPage<SysDept> iPage) {
        return sysDeptMapper.selectSysDeptList(sysDept, iPage);
    }

    /**
     * 校验部门名称是否存在
     *
     * @param id
     * @param deptName
     * @param parentId
     * @return 结果  true：存在     false：不存在
     */
    public Boolean checkDeptNameUnique(String id, String deptName, String parentId) {
        SysDept sysDept = sysDeptMapper.checkDeptNameUnique(deptName, parentId);
        if (ObjectUtils.isNotEmpty(sysDept)) {
            if (sysDept.getId().equals(id)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 修改部门
     *
     * @param viewForm 部门
     * @return 结果
     */
    @Override
    public ResponseResult updateSysDept(SysDeptUpdateViewForm viewForm) {
        //检查部门名称在当前节点是否存在
        if (checkDeptNameUnique(viewForm.getId(), viewForm.getDeptName(), viewForm.getParentId())) {
            throw new RuntimeException("修改部门'" + viewForm.getDeptName() + "'失败，角色部门已存在");
        }
        SysDept parentDept = sysDeptMapper.selectById(viewForm.getParentId());
        SysDept oldDept = sysDeptMapper.selectById(viewForm.getId());
        SysDept sysDept = SysDeptMapStruct.INSTANCE.updateViewFormToSysDept(viewForm);
        sysDept.preUpdate();
        if (ObjectUtils.isNotEmpty(parentDept) && ObjectUtils.isNotEmpty(oldDept)) {
            String newParents = parentDept.getParentIds() + "," + parentDept.getId();
            String oldParents = oldDept.getParentIds();
            sysDept.setParentIds(newParents);
            updateDeptChildren(sysDept.getId(), newParents, oldParents);
        }
        sysDeptMapper.updateById(sysDept);
        return ResponseResult.success("修改成功");
    }

    /**
     * 修改子元素关系
     *
     * @param id         被修改的部门ID
     * @param newParents 新的父ID集合
     * @param oldParents 旧的父ID集合
     */
    private void updateDeptChildren(String id, String newParents, String oldParents) {
        List<SysDept> children = sysDeptMapper.selectChildrenDeptById(id);
        for (SysDept child : children) {
            child.setParentIds(child.getParentIds().replace(oldParents, newParents));
        }
        if (children.size() > 0) {
            sysDeptMapper.updateDeptChildren(children);
        }
    }


    /**
     * 删除部门信息
     *
     * @param id 部门ID
     * @return 结果
     */
    @Override
    public Integer deleteSysDeptById(String id) {
        Integer count = sysDeptMapper.hasChildByDeptId(id);
        if (count > 0) {
            throw new RuntimeException("存在下级部门,不允许删除");
        }
        Integer num = sysDeptMapper.checkDeptExistUser(id);
        if (num > 0) {
            throw new RuntimeException("部门存在用户,不允许删除");
        }

        return sysDeptMapper.deleteById(id);
    }


    @Override
    @DataScope(deptAlias = "d")
    public List<TreeSelect> selectDeptList(SysDept sysDept) {
        List<SysDeptVO> list = sysDeptMapper.selectDeptList(sysDept);
        return buildDeptTreeSelect(list);
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Integer> selectDeptListByRoleId(String roleId) {
        return sysDeptMapper.selectDeptListByRoleId(roleId);
    }

    /**
     * 新增部门
     *
     * @param viewForm 部门
     * @return 结果
     */
    @Override
    public ResponseResult insertSysDept(SysDeptInsertViewForm viewForm) {
        //检查部门名称在当前节点是否存在
        if (checkDeptNameUnique(null, viewForm.getDeptName(), viewForm.getParentId())) {
            throw new RuntimeException("新增部门'" + viewForm.getDeptName() + "'失败，角色部门已存在");
        }
        //如果父节点不为正常状态,则不允许新增子节点
        SysDept parentDept = sysDeptMapper.selectById(viewForm.getParentId());
        if (UN_ENABLE.equals(parentDept.getEnableFlag())) {
            throw new RuntimeException("部门停用，不允许新增");
        }
        SysDept sysDept = SysDeptMapStruct.INSTANCE.insertViewFormToSysDept(viewForm);
        //以下内容会写成公共方法
        sysDept.preInsert();
        sysDept.setParentIds(parentDept.getParentIds() + "," + sysDept.getParentId());
        sysDept.setOrgId(sysDept.getId());
        sysDeptMapper.insert(sysDept);
        return ResponseResult.success("新增成功");
    }


    /**
     * 根据父id获取部门
     *
     * @param sysDept
     * @return
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDeptVO> getListByParentId(SysDept sysDept) {
        if (StringUtils.isBlank(sysDept.getParentId())) {
            sysDept.setParentId("0");
        }
        List<SysDeptVO> list = sysDeptMapper.getListByParentId(sysDept);
        for (SysDeptVO deptVO : list) {
            SysDept sysDept1 = new SysDept();
            sysDept1.setParentId(deptVO.getId());
            List<SysDeptVO> list1 = sysDeptMapper.getListByParentId(sysDept1);
            if (list1.isEmpty()) {
                deptVO.setHasChildren(false);
            } else {
                deptVO.setHasChildren(true);
            }
        }
        return list;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param list 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<SysDeptVO> list) {
        List<SysDeptVO> deptTrees = buildDeptTree(list);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<SysDeptVO> buildDeptTree(List<SysDeptVO> depts) {
        List<SysDeptVO> returnList = new ArrayList<SysDeptVO>();
        for (Iterator<SysDeptVO> iterator = depts.iterator(); iterator.hasNext(); ) {
            SysDeptVO t = (SysDeptVO) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if ("0".equals(t.getParentId())) {
                recursionFn(depts, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDeptVO> list, SysDeptVO t) {
        // 得到子节点列表
        List<SysDeptVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDeptVO tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysDeptVO> it = childList.iterator();
                while (it.hasNext()) {
                    SysDeptVO n = (SysDeptVO) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDeptVO> getChildList(List<SysDeptVO> list, SysDeptVO t) {
        List<SysDeptVO> tlist = new ArrayList<SysDeptVO>();
        Iterator<SysDeptVO> it = list.iterator();
        while (it.hasNext()) {
            SysDeptVO n = (SysDeptVO) it.next();
            if (t.getId().equals(n.getParentId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDeptVO> list, SysDeptVO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
