package com.my.app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;
import java.util.TreeSet;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {
    // 不需要授权的URI
    private Set<String> whitelistURIS = new TreeSet<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (whitelistURIS.contains(requestURI)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        // 检查是否授权
        boolean checkAuth = false;
        if (!checkAuth) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print("{\"code\":\"401\",\"message\":\"unauthorized\"}");
        }
        return checkAuth;
    }
}