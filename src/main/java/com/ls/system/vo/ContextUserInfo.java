package com.ls.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/3/16 16:02
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class ContextUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建人id
     * 不能为空
     */
    public String createUserId;
    /**
     * 创建人
     * 不能为空
     */
    public String createUser;
    /**
     * 创建时间
     * 不能为空
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime createDate;
    /**
     * 更新人id
     * 不能为空
     */

    public String updateUser;
    /**
     * 更新人id
     * 不能为空
     */

    public String updateUserId;
    /**
     * 更新时间
     * 不能为空
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime updateDate;

    public String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * 身份证号
     */
    private String idCard;

    private String id;
    /**
     * 排序
     */
    private Integer customSort;

    /**
     * 头像
     */
    private String imgPath;

    /**
     * 是否是管理员
     */
    private Boolean isAdmin;

    /**
     * 用户 部门
     */
    private SysDeptVO depts;
    /**
     * 用户 角色
     */
    private List<SysRoleVO> roles;

}
