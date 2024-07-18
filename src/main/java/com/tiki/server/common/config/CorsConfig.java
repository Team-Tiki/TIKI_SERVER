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
		val source = setUrlBasedCorsConfigurationSource();
		return new CorsFilter(source);
	}

	private UrlBasedCorsConfigurationSource setUrlBasedCorsConfigurationSource() {
		val source = new UrlBasedCorsConfigurationSource();
		val config = setCorsConfiguration();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	private CorsConfiguration setCorsConfiguration() {
		val config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(("https://tiki-client.vercel.app"));
		config.addAllowedOrigin("http://localhost:5173");
		config.addAllowedHeader("*");
		config.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		return config;
	}
}
