package com.ls.system.entity;

import com.ls.system.constant.Constants;
import com.ls.system.entity.common.PreventionCommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典数据表 sys_dict_data
 *
 * @author lsbdp
 */
@Data
public class SysDictData extends PreventionCommonEntity {

    /**
     * 字典标签
     */
    private String dictLabel;

    /**
     * 字典键值
     */
    private String dictValue;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格字典样式
     */
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    private String isDefault;


    public boolean getDefault() {
        return Constants.YES.equals(this.isDefault) ? true : false;
    }

}
