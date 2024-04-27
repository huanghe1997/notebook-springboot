package com.henry.config;

import com.henry.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    JwtInterceptor jwtInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器方法 传入我们自己的拦截器  这里的拦截器，是使用的JWT定义的拦截器
        registry.addInterceptor(jwtInterceptor).addPathPatterns(("/api/**"))
                .excludePathPatterns("/api/auth/**");
    }

}
