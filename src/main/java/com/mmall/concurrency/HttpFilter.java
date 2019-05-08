package com.mmall.concurrency;

import com.mmall.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//HttpFilter 过滤器
@Slf4j
public class HttpFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException{
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("do filter , "+ Thread.currentThread().getId() + " "+request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        //如果不对这个请求做拦截，则执行如下操作
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {

    }
}
