package com.example.demo.springboot.interceptor;

import com.example.demo.springboot.interceptor.MainInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public MainInterceptor myAuthInterceptor() {
        return new MainInterceptor();
    }

    /**
     * 添加拦截器 拦截所有请求
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myAuthInterceptor()).addPathPatterns("/**");
    }
}
