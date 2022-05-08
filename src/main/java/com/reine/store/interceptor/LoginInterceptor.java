package com.reine.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个拦截器
 *
 * @author reine
 * 2022/5/7 8:24
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象是否有uid数据，有则放行，没有重定向到登录
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器
     * @return true放行，false拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if (uid == null) {
            // 用户未登录，重定向到登录页
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
