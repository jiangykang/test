package com.ls.system.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @Author: Min.Hu
 * Time: 2020/4/27 14:10
 * <p>  生成自定义id
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
public class GenerateIdUtil {
    public static String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter) + UUID.randomUUID().toString().toLowerCase().substring(30);
    }

}
