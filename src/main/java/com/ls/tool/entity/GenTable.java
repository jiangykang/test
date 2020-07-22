package com.ls.tool.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls.tool.constant.GenConstants;
import com.ls.system.utils.StringUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务表 gen_table
 *
 * @author lsbdp
 */
@Data
public class GenTable implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Long tableId;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    /**
     * 实体类名称(首字母大写)
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String tplCategory;

    /**
     * 生成包路径
     */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    /**
     * 生成模块名
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    /**
     * 生成业务名
     */
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    /**
     * 生成功能名
     */
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /**
     * 主键信息
     */
    private GenTableColumn pkColumn;

    /**
     * 表列信息
     */
    @Valid
    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
    private String options;

    /**
     * 树编码字段
     */
    private String treeCode;

    /**
     * 树父编码字段
     */
    private String treeParentCode;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 树名称字段
     */
    private String treeName;

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && GenConstants.TPL_TREE.equals(tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && GenConstants.TPL_CRUD.equals(tplCategory);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.TREE_ENTITY);
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }
}