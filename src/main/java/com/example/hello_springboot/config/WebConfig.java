package com.example.hello_springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoggingInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") //全てのパスに対して
                .allowedOrigins("http://localhost:3000") //ReactのURL
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); //必要ならCookieやAuthorozationヘッダーを許可
    }
}

