package com.bishe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 基于URL实现的拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        System.out.println("请求URL：" + path);
        if (path.contains("/api/login")) {
            //不需要的拦截直接过
            return true;
        } else {
            HttpSession session = request.getSession();
            String id = request.getHeader("userId");
            System.out.println("userId=" + id);
            Object userId = session.getAttribute(id);
            if (userId != null) {
                return true;
            } else {
                System.out.println("==================权限不够==================");
                return false;
            }
        }
    }
}
