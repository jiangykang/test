package com.ls.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

/**
 * @Author: Min.Hu
 * Time: 2020/3/31 10:41
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/

@Configuration
public class TaskPoolConfig {

    @Bean("task")
    public Executor taskExecutor() {
        ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
        //线程数
        threadPool.setPoolSize(20);
        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        threadPool.setThreadNamePrefix("task-");
        //设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁
        threadPool.setAwaitTerminationSeconds(60);
        return threadPool;
    }

}
