package com.ls.system.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.ls.system.utils.GenerateIdUtil;
import com.ls.system.entity.SysDept;
import com.ls.system.entity.SysUserRole;
import com.ls.system.mapper.*;
import com.ls.system.service.SysUtilService;
import com.ls.system.utils.RedisCache;
import com.ls.system.viewform.sysuser.*;
import com.ls.system.vo.SysRoleVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.system.config.ResponseResult;
import com.ls.system.entity.SysUser;
import com.ls.system.mapstruct.SysUserMapStruct;
import com.ls.system.service.SysUserService;
import com.ls.system.utils.PasswordUtils;
import com.ls.system.utils.UserInfoUtils;
import com.ls.system.vo.ContextUserInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.ls.system.constant.Constants.*;
import static com.ls.system.constant.Constants.ENABLE;
import static com.ls.system.constant.Constants.INITIAL_PASSWORD;
import static com.ls.system.utils.RSAUtils.PRIVATE_KEY;
import static com.ls.system.utils.RSAUtils.PUBLIC_KEY;
import static com.ls.system.utils.RSAUtils.decrypt;
import static com.ls.system.utils.RSAUtils.genKeyPair;

/**
 * @Author: Min.Hu
 * Time: 2020/3/10 16:42
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysUtilService sysUtilService;

    /**
     * 查询用户列表
     *
     * @param iPage viewForm
     * @return 用户
     */
    @Override
    public IPage<SysUser> selectSysUserList(Page<SysUser> iPage, SysUserListViewForm viewForm) {
        return sysUserMapper.selectSysUserList(iPage, viewForm);
    }


    /**
     * 新增用户
     *
     * @param viewForm 用户
     * @return 结果
     */
    @Override
    public ResponseResult insertSysUser(SysUserInsertViewForm viewForm) {
        if (StringUtils.isNotBlank(viewForm.getJobNumber()) && checkJobNumber(null, viewForm.getJobNumber())) {
            throw new RuntimeException("新增人员'" + viewForm.getUserName() + "'失败，用户工号已存在");
        }
        if (StringUtils.isNotBlank(viewForm.getIdCard()) && checkIdCard(null, viewForm.getIdCard())) {
            throw new RuntimeException("新增人员'" + viewForm.getUserName() + "'失败，用户身份证已存在");
        }

        SysUser sysUser = SysUserMapStruct.INSTANCE.insertViewFormToSysUser(viewForm);
        sysUser.preInsert();
        //获取部门名称
        SysDept sysDept = sysDeptMapper.selectById(sysUser.getDeptId());
        sysUser.setDeptName(sysDept.getDeptName());
        sysUserMapper.insert(sysUser);

        List<SysUserRole> list = new ArrayList<>(10);
        for (String role : viewForm.getRoles()) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(GenerateIdUtil.generateId());
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(role);
            list.add(sysUserRole);
        }
        sysUserRoleMapper.batchUserRole(list);

        return ResponseResult.success("新增成功");
    }

    /**
     * 修改用户
     *
     * @param viewForm 用户
     * @return 结果
     */
    @Override
    public ResponseResult updateSysUser(SysUserUpdateViewForm viewForm) {
        if (StringUtils.isNotBlank(viewForm.getJobNumber()) && checkJobNumber(viewForm.getId(), viewForm.getJobNumber())) {
            throw new RuntimeException("修改人员'" + viewForm.getUserName() + "'失败，用户工号已存在");
        }
        if (StringUtils.isNotBlank(viewForm.getIdCard()) && checkIdCard(viewForm.getId(), viewForm.getIdCard())) {
            throw new RuntimeException("修改人员'" + viewForm.getUserName() + "'失败，用户身份证已存在");
        }
        SysUser oldSysUser = sysUserMapper.selectById(viewForm.getId());
        SysUser sysUser = SysUserMapStruct.INSTANCE.updateViewFormToSysUser(viewForm);
        sysUser.preUpdate();
        if (StringUtils.isNotBlank(sysUser.getDeptId())) {
            SysDept sysDept = sysDeptMapper.selectById(sysUser.getDeptId());
            sysUser.setDeptName(sysDept.getDeptName());
        }
        //修改密码
        if (StringUtils.isNotBlank(viewForm.getOldPassword()) && StringUtils.isNotBlank(viewForm.getPassword())) {
            String oldPassword = PasswordUtils.generatePwdEncrypt(viewForm.getOldPassword(), oldSysUser.getSalt());
            if (!oldSysUser.getPassword().equals(oldPassword)) {
                throw new RuntimeException("旧密码错误");
            }
            sysUser.setPassword(PasswordUtils.generatePwdEncrypt(viewForm.getPassword(), oldSysUser.getSalt()));
        }
        sysUserMapper.updateById(sysUser);
        //先删除所有角色 然后添加
        sysUserRoleMapper.deleteUserRoleByUserId(sysUser.getId());
        List<SysUserRole> list = new ArrayList<>(10);
        for (String role : viewForm.getRoles()) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(GenerateIdUtil.generateId());
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(role);
            list.add(sysUserRole);
        }
        sysUserRoleMapper.batchUserRole(list);
        return ResponseResult.success("修改成功");
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    public ResponseResult deleteSysUserByIds(String[] ids) {

        List<String> list = Arrays.asList(ids);
        //此处是真实删除  后期根据业务 可自由定义成逻辑删除
        sysUserMapper.deleteBatchIds(list);
        return ResponseResult.success("删除成功");
    }

    @Override
    public Map<String, Object> getLoginUser() {

        //获取当前登录人
        ContextUserInfo sysUser = UserInfoUtils.getLoginUser();
        sysUser = sysUserMapper.selectUserByAccount(sysUser.getJobNumber());
        boolean adminFlag = false;
        for (SysRoleVO role : sysUser.getRoles()) {
            if ("admin".equals(role.getRoleKey())) {
                adminFlag = true;
                break;
            }
        }
        sysUser.setIsAdmin(adminFlag);
        //查询当前登录人的角色
        Set<String> list = sysRoleMapper.selectAllRoleKeyByUserId(sysUser.getId());
        Set<String> permission = new HashSet<String>();
        if (sysUser.getIsAdmin()) {
            permission.add("*:*:*");
        } else {
            permission = sysMenuMapper.selectAllPermissionByUserId(sysUser.getId());
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("user", sysUser);
        map.put("roles", list);
        map.put("permissions", permission);
        return map;
    }

    /**
     * 启用停用
     *
     * @param viewForm
     * @return
     */
    @Override
    public ResponseResult enable(SysUserEnableViewForm viewForm) {
        SysUser sysUser = new SysUser();
        sysUser.setId(viewForm.getId());
        sysUser.setEnableFlag(viewForm.getEnableFlag());
        sysUser.preUpdate();
        int count = sysUserMapper.updateById(sysUser);
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

    /**
     * 根据工号查询人员信息
     *
     * @param jobNumber
     * @return
     */
    @Override
    public SysUser getUserInfoByJobNumber(String jobNumber) {
        SysUser sysUser = new SysUser();
        sysUser.setJobNumber(jobNumber);
        Wrapper<SysUser> wrapper = new QueryWrapper<>(sysUser);

        return sysUserMapper.selectOne(wrapper);
    }

    /**
     * 修改密码
     *
     * @param viewForm
     * @return
     */
    @Override
    public ResponseResult editPwd(SysUserEditPwdViewForm viewForm) throws Exception {
        String pri = redisCache.getCacheObject(SecurityUtils.getSubject().getSession().getId().toString());
        //解密后的新密码
        String newPwd = decrypt(viewForm.getPassword(), pri);
        //解密后的新密码
        String oldPwd = decrypt(viewForm.getOldPassword(), pri);
        SysUser sysUser = new SysUser();
        sysUser.setId(viewForm.getId());
        SysUser oldSysUser = sysUserMapper.selectById(viewForm.getId());
        String oldPassword = PasswordUtils.generatePwdEncrypt(oldPwd, oldSysUser.getSalt());
        if (!oldSysUser.getPassword().equals(oldPassword)) {
            throw new RuntimeException("原密码错误");
        }
        String salt = PasswordUtils.generateSalt();
        //新密码盐
        sysUser.setSalt(salt);
        //新密码
        sysUser.setPassword(PasswordUtils.generatePwdEncrypt(newPwd, salt));
        sysUserMapper.updateById(sysUser);
        return ResponseResult.successMsg("修改成功");
    }

    /**
     * 修改密码前获取公钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public ResponseResult getPubKey() throws NoSuchAlgorithmException {
        //加密相关
        Map<String, String> keyMap = genKeyPair();
        //公钥
        String pub = keyMap.get(PUBLIC_KEY);
        //私钥
        String pri = keyMap.get(PRIVATE_KEY);
        redisCache.setCacheObject(SecurityUtils.getSubject().getSession().getId().toString(), pri, CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return ResponseResult.success(pub, "获取成功");
    }

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    @Override
    public ResponseResult uploadImg(MultipartFile file) {
        ResponseResult responseResult = sysUtilService.uploadFile(file);
        SysUser sysUser = new SysUser();
        sysUser.setId(UserInfoUtils.getLoginUser().getId());
        sysUser.preUpdate();
        sysUser.setImgPath(responseResult.getData().toString());
        sysUserMapper.updateById(sysUser);
        return ResponseResult.successMsg("头像上传成功");
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult resetPwd(String id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new RuntimeException("用户不存在");
        }
        sysUser.setPassword(PasswordUtils.generatePwdEncrypt(INITIAL_PASSWORD, sysUser.getSalt()));
        sysUser.preUpdate();
        sysUserMapper.updateById(sysUser);
        return ResponseResult.success("重置成功");
    }


    /**
     * 校验工号称是否存在
     *
     * @param jobNumber 工号
     * @param id        id
     * @return 结果
     */
    public Boolean checkJobNumber(String id, String jobNumber) {
        if (StringUtils.isBlank(jobNumber)) {
            throw new RuntimeException("工号不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setJobNumber(jobNumber);
        Wrapper wrapper = new QueryWrapper(sysUser);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(user)) {
            if (user.getId().equals(id)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 校验身份证称是否存在
     *
     * @param idCard 身份证
     * @param id     id
     * @return 结果
     */
    public Boolean checkIdCard(String id, String idCard) {
        if (StringUtils.isBlank(idCard)) {
            throw new RuntimeException("身份证号不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setIdCard(idCard);
        Wrapper wrapper = new QueryWrapper(sysUser);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(user)) {
            if (user.getId().equals(id)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


}
