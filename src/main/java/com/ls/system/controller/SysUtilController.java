package com.ls.system.controller;

import com.ls.system.config.ResponseResult;
import com.ls.system.service.SysUtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Min.Hu
 * Time: 2020/4/23 9:44
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Api(tags = {"系统 共用"})
@RestController
@RequestMapping("/system/util")
public class SysUtilController {

    @Autowired
    SysUtilService sysUtilService;

    @PostMapping(value = "/uploadFile")
    @ApiOperation("上传单个附件")
    public ResponseResult uploadFile(MultipartFile file) {
        return sysUtilService.uploadFile(file);
    }

}
