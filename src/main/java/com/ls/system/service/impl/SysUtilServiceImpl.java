package com.ls.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ls.system.config.ResponseResult;
import com.ls.system.service.SysUtilService;
import com.ls.system.utils.OssUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Min.Hu
 * Time: 2020/4/23 9:48
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Service
public class SysUtilServiceImpl implements SysUtilService {
    /**
     * 上传单个附件
     *
     * @param file
     * @return
     */
    @Override
    public ResponseResult uploadFile(MultipartFile file) {
        ResponseResult upload = OssUploadUtil.upload(file, true);
        JSONObject json = (JSONObject) upload.getData();
        return ResponseResult.success(json.get("url"), "上传成功");
    }
}
