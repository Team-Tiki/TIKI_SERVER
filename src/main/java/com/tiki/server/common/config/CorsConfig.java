package com.tiki.server.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.val;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = setUrlBasedCorsConfigurationSource();
        return new CorsFilter(source);
    }

    private UrlBasedCorsConfigurationSource setUrlBasedCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = setCorsConfiguration();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private CorsConfiguration setCorsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(("https://ti-kii.com"));
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("https://www.tiki-sopt.p-e.kr");
        config.addAllowedHeader("*");
        config.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        return config;
    }
}
