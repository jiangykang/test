package com.ls.system.controller;

import com.ls.system.config.ResponseResult;
import com.ls.system.entity.Server;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author lsbdp
 */
@Api(tags = {"系统 服务器监控"})
@RestController
@RequestMapping("/monitor/server")
public class SysServerController {
    @GetMapping()
    @ApiOperation("获取服务器信息")
    @RequiresPermissions("monitor:server:list")
    public ResponseResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return ResponseResult.success(server);
    }
}
