package com.end.demo.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/calendar/**")
                .addPathPatterns("/mypage")
                .addPathPatterns("/mypage/**")
                .excludePathPatterns("/"); //제외할 url

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/cms/**")
                .excludePathPatterns("/cms")
                .excludePathPatterns("/cms/admin_login");

    }
}
