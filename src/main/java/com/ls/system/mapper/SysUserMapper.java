package com.ls.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ls.system.entity.SysUser;
import com.ls.system.viewform.sysuser.SysUserListViewForm;
import com.ls.system.vo.ContextUserInfo;

/**
 * 用户表 数据层
 *
 * @author lsbdp
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 通过用户名查询用户
     *
     * @param account 用户名
     * @return 用户对象信息
     */
    public ContextUserInfo selectUserByAccount(String account);

    /**
     * 查询用户列表
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    public IPage<SysUser> selectSysUserList(Page<SysUser> iPage, @Param("viewForm") SysUserListViewForm viewForm);

    /**
     * 获取所有id
     *
     * @return
     */
    List<String> selectAllId();
}
