package com.ls.system.utils;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * 时间工具类
 * 
 * @author lsbdp
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{

    public static final String parseDateToStr(final String format, final LocalDateTime date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取服务器启动时间
     *
     * @return
     */
    public static LocalDateTime getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(LocalDateTime endDate, LocalDateTime nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli() - nowDate.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
