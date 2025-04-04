package org.example.concurrency.httpInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.concurrency.util.ThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.info("[postHandle] postHandle {}", ThreadLocalUtil.getThreadLocal());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //log.info("[afterCompletion] afterCompletion {}", ThreadLocalUtil.getThreadLocal());
        ThreadLocalUtil.removeThreadLocal();
        //log.info("[afterCompletion] afterCompletion {}", ThreadLocalUtil.getThreadLocal());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("[preHandle] preHandle {}", ThreadLocalUtil.getThreadLocal());
        return true;
    }
}
