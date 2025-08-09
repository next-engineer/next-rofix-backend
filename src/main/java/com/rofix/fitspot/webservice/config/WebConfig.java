package com.rofix.fitspot.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${spring.cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${spring.cors.allowed-methods}")
    private String[] allowedMethods;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 경로에 대해
                        .allowedOrigins(allowedOrigins)
                        .allowedMethods(allowedMethods) // 허용할 HTTP 메소드
                        .allowedHeaders("*") // 모든 헤더 허용
                        .allowCredentials(true) // 인증 정보 포함 허용 (프론트에서 credentials: true 쓸 경우 필요)
                        .maxAge(3600); // Pre-flight 요청 캐시 시간 (초)
            }
        };
    }
}
