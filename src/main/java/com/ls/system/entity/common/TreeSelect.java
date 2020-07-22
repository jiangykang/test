package com.ls.system.entity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ls.system.vo.SysDeptVO;
import com.ls.system.vo.SysMenuVO;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Min.Hu
 * Time: 2020/3/10 14:41
 * <p> 数型 结构
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public class TreeSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private String id;



    /**
     * 前端空间使用value保存id
     */
    private String value;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect() {

    }

    public TreeSelect(SysDeptVO dept) {
        this.id = dept.getId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren() == null ? null : dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenuVO menu) {
        this.id = menu.getMenuId();
        this.value = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren() == null ? null : menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeSelect> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelect> children) {
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

