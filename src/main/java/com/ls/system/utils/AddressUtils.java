package com.ls.system.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取地址类
 *
 * @author lsbdp
 */
@Slf4j
public class AddressUtils {
    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
        if (StringUtils.isBlank(rspStr)) {
            log.error("获取地理位置异常 {}", ip);
            return address;
        }
        JSONObject obj = JSONObject.parseObject(rspStr);
        JSONObject data = obj.getObject("data", JSONObject.class);
        String region = data.getString("region");
        String city = data.getString("city");
        address = region + " " + city;
        return address;
    }
}
