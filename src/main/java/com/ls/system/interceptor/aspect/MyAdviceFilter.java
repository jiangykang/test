package com.ls.system.interceptor.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: Min.Hu
 * Time: 2020/3/9 17:05
 * <p>
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * Good Luck
 **/
@Slf4j
//@Configuration
public class MyAdviceFilter extends AdviceFilter {
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        System.out.println("====前处理/前置返回处理");
        return true;//返回 false 将中断后续拦截器链的执行
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) {
        System.out.println("====后处理/后置返回处理");
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) {
        System.out.println("====完成处理/后置最终处理");
    }
}
