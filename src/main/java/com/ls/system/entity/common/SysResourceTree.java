package com.ls.system.entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Min.Hu
 * Time: 2020/4/10 10:36
 * <p>  前端菜单 格式
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Data
public class SysResourceTree implements Serializable {

    private static final long serialVersionUID = 1L;

    private String label;

    private String menuId;

    private String parentMenuId;

    private String path;

    private String component;

    private String icon;

    private String isMenu;

    private String isCheck;

    private List<SysResourceTree> children = new ArrayList<SysResourceTree>(); // 子节点


}
