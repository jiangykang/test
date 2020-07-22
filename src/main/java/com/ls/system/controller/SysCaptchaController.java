package com.ls.system.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import com.ls.system.interceptor.annotation.Log;
import com.ls.system.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ls.system.config.ResponseResult;
import com.ls.system.constant.Constants;
import com.ls.system.utils.RedisCache;
import com.ls.system.utils.VerifyCodeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static com.ls.system.utils.RSAUtils.*;
import static com.ls.system.utils.RSAUtils.PRIVATE_KEY;
import static com.ls.system.utils.RSAUtils.PUBLIC_KEY;
import static com.ls.system.utils.RSAUtils.genKeyPair;

/**
 * @Author: Min.Hu
 * Time: 2020/3/12 17:10
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@RestController
@Api(tags = "系统 验证码相关")
public class SysCaptchaController {
    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @ApiOperation("生成验证码")
    @Log(title = "生成验证码")
    @GetMapping("/captchaImage")
    public ResponseResult getCode(HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = UUID.randomUUID().toString();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        //加密相关
        Map<String, String> keyMap = genKeyPair();
        //公钥
        String pub = keyMap.get(PUBLIC_KEY);
        //私钥
        String pri = keyMap.get(PRIVATE_KEY);
        redisCache.setCacheObject(uuid, pri, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        redisCache.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            Map<String, Object> map = new HashMap<>(16);
            map.put("uuid", uuid);
            map.put(PUBLIC_KEY, pub);
            map.put("img", Base64Utils.encode(stream.toByteArray()));
            return ResponseResult.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failure(e.getMessage());
        } finally {
            stream.close();
        }
    }

}
