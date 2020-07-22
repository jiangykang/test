package com.ls.system.config;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @Author: Min.Hu
 * Time: 2020/3/30 13:38
 * <p>Configuration主要用于标记配置类，兼备Component的效果。
 * EnableScheduling开启定时任务
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
//@Configuration
//@EnableScheduling
public class ScheduleTask {

    //3.添加定时任务
    @Scheduled(cron = "0/30 * * * * ?")
    //或直接指定时间间隔，例如：30秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}