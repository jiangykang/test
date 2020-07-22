package com.ls.system.service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysUser;
import com.ls.system.viewform.sysuser.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Min.Hu
 * Time: 2020/3/10 16:41
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户列表
     *
     * @param iPage
     * @param viewForm
     * @return
     */
    IPage<SysUser> selectSysUserList(Page<SysUser> iPage, SysUserListViewForm viewForm);

    /**
     * 新增用户
     *
     * @param viewForm 用户
     * @return 结果
     */
    ResponseResult insertSysUser(SysUserInsertViewForm viewForm);

    /**
     * 修改用户
     *
     * @param viewForm 用户
     * @return 结果
     */
    ResponseResult updateSysUser(SysUserUpdateViewForm viewForm);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    ResponseResult deleteSysUserByIds(String[] ids);

    /**
     * 登录成功后获取用户详细信息
     *
     * @return
     */
    Map<String, Object> getLoginUser();


    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    ResponseResult resetPwd(String id);

    /**
     * 启用停用
     *
     * @param viewForm
     * @return
     */
    ResponseResult enable(SysUserEnableViewForm viewForm);

    /**
     * 根据工号查询用户信息
     *
     * @param jobNumber
     * @return
     */
    SysUser getUserInfoByJobNumber(String jobNumber);

    /**
     * 修改密码
     *
     * @param viewForm
     * @return
     * @throws Exception
     */
    ResponseResult editPwd(SysUserEditPwdViewForm viewForm) throws Exception;

    /**
     * 修改密码前获取公钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    ResponseResult getPubKey() throws NoSuchAlgorithmException;

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    ResponseResult uploadImg(MultipartFile file);
}
