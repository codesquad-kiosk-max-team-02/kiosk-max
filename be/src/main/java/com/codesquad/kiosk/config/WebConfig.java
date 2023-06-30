package com.codesquad.kiosk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:3000")
                    .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.HEAD.name(),
                        HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name());
            }
        };
    }
}
