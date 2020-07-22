package com.ls.system.service;

import com.ls.system.config.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Min.Hu
 * Time: 2020/4/23 9:48
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public interface SysUtilService {
    /**
     * 上传单个附件
     *
     * @param file
     * @return
     */
    ResponseResult uploadFile(MultipartFile file);
}
