package com.ls.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 15:31
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class SysDeptVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门ID
     */
    private String parentId;

    /**
     * 父部门IDs
     */
    private String parentIds;

    /**
     * 等级
     */
    private Integer grade;
    /**
     * 启用
     */
    private String enableFlag;

    public String remark;

    private Integer customSort;
    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 是否有子类
     */
    private Boolean hasChildren;

    private List<SysDeptVO> children;
}
