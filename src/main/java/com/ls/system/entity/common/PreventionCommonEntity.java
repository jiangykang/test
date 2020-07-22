package com.ls.system.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls.system.interceptor.annotation.Excel;
import com.ls.system.utils.GenerateIdUtil;
import com.ls.system.utils.UserInfoUtils;
import com.ls.system.vo.ContextUserInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Min.Hu
 * Time: 2020/3/10 14:41
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class PreventionCommonEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 创建人
     */
    @Excel(name = "创建人")
    public String createUser;
    /**
     * 创建人id
     */
    public String createUserId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "创建时间")
    public LocalDateTime createDate;
    /**
     * 修改人
     */
    @Excel(name = "修改人")
    public String updateUser;
    /**
     * 修改人id
     */
    public String updateUserId;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime updateDate;
    /**
     * 删除标志 0:未删除 1:删除
     */
    @Excel(name = "删除标志 ", readConverterExp = "false=未删除, true=删除")
    public Boolean deleteFlag;
    /**
     * 启用标志 0:未启用 1:启用
     */
    @Excel(name = "启用标志 ")
    public String enableFlag;
    /**
     * 备注
     */
    @Excel(name = "备注")
    public String remark;
    /**
     * 主键id
     */
    @TableId
    @Excel(name = "主键id", cellType = Excel.ColumnType.STRING)
    private String id;
    /**
     * 自定义排序
     */
    @Excel(name = "自定义排序")
    private Integer customSort;
    /**
     * 数据权限
     */
    @TableField(exist = false)
    private String dataScope;

    public void preInsert() {
        ContextUserInfo userInfo = UserInfoUtils.getLoginUser();
        this.setId(GenerateIdUtil.generateId());
        this.setCreateDate(LocalDateTime.now());
        this.setCreateUser(userInfo.getUserName());
        this.setCreateUserId(userInfo.getId());
        this.setUpdateDate(LocalDateTime.now());
        this.setUpdateUser(userInfo.getUserName());
        this.setUpdateUserId(userInfo.getId());
        this.setDeleteFlag(false);


    }

    public void preUpdate() {
        ContextUserInfo userInfo = UserInfoUtils.getLoginUser();
        this.setUpdateDate(LocalDateTime.now());
        this.setUpdateUser(userInfo.getUserName());
        this.setUpdateUserId(userInfo.getId());
    }
}
