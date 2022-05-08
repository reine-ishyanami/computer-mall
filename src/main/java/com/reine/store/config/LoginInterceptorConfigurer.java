package com.reine.store.config;

import com.reine.store.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 处理器拦截器的注册
 *
 * @author reine
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
        // 注册自定义拦截器
        registry.addInterceptor(interceptor)
                // 拦截的url
                .addPathPatterns("/**")
                // 白名单
                .excludePathPatterns(patterns);
    }
}
