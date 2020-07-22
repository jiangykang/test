package com.ls.system.viewform.sysoperlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 操作日志记录对象 sys_oper_log  ViewForm
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 *
 * @Author Min.Hu
 * @date 2020-04-01
 */
@Data
@ApiModel("操作日志记录对象查询参数")
public class SysOperLogListViewForm {


    /**
     * 模块标题
     */
    @ApiModelProperty(value = "模块标题")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @ApiModelProperty(value = "业务类型（0其它 1新增 2修改 3删除）")
    private String businessType;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称")
    private String method;

    /**
     * 请求方式
     */
    @ApiModelProperty(value = "请求方式")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @ApiModelProperty(value = "操作类别（0其它 1后台用户 2手机端用户）")
    private String operatorType;

    /**
     * 操作人员
     */
    @ApiModelProperty(value = "操作人员")
    private String operName;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 请求URL
     */
    @ApiModelProperty(value = "请求URL")
    private String operUrl;

    /**
     * 主机地址
     */
    @ApiModelProperty(value = "主机地址")
    private String operIp;

    /**
     * 操作地点
     */
    @ApiModelProperty(value = "操作地点")
    private String operLocation;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String operParam;

    /**
     * 返回参数
     */
    @ApiModelProperty(value = "返回参数")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @ApiModelProperty(value = "操作状态（0正常 1异常）")
    private String status;

    /**
     * 错误消息
     */
    @ApiModelProperty(value = "错误消息")
    private String errorMsg;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
