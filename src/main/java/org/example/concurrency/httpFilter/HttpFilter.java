package org.example.concurrency.httpFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.concurrency.util.ThreadLocalUtil;

import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest servletReq = (HttpServletRequest) servletRequest;
        //servletReq.getSession().getAttribute("");
        ThreadLocalUtil.setThreadLocal(Thread.currentThread().getName());
        log.info("[HttpFilter] thread name {}", Thread.currentThread().getName());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
