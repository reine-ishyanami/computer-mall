package com.reine.store.config;

import com.reine.store.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 *
 * @author reine
 * @since 2022/5/7 8:40
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    /**
     * 自定义拦截器对象
     */
    private HandlerInterceptor interceptor = new LoginInterceptor();

    @Value("#{'${filter.white.list}'.split(',')}")
    private List<String> patterns;

    /**
     * 配置白名单
     */

    /**
     * 配置拦截器
     *
     * @param registry 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置白名单
        // List<String> patterns = new ArrayList<>();
        // patterns.add("/bootstrap3/**");
        // patterns.add("/css/**");
        // patterns.add("/images/**");
        // patterns.add("/js/**");
        // patterns.add("/web/register.html");
        // patterns.add("/web/login.html");
        // patterns.add("/web/index.html");
        // patterns.add("/web/product.html");
        // patterns.add("/user/register");
        // patterns.add("/user/login");
        // 注册自定义拦截器
        registry.addInterceptor(interceptor)
                // 拦截的url
                .addPathPatterns("/**")
                // 白名单
                .excludePathPatterns(patterns);
    }
}
