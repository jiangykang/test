package com.ls.system.entity;

import com.ls.system.entity.common.PreventionCommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典类型表 sys_dict_type
 *
 * @author lsbdp
 */
@Data
public class SysDictType extends PreventionCommonEntity {


    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;
}
