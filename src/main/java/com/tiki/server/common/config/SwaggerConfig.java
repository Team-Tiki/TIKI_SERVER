package com.tiki.server.common.config;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.val;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApi() {
		val securityScheme = new SecurityScheme();
		securityScheme.setType(HTTP);
		securityScheme.setScheme("bearer");
		securityScheme.setBearerFormat("JWT");

		val components = new Components();
		components.addSecuritySchemes("BearerAuthentication", securityScheme);

		val securityRequirement = new SecurityRequirement();
		securityRequirement.addList("BearerAuthentication");

		val info = new Info();
		info.setTitle("TIKI API Document");
		info.setDescription("티키 API 명세서");
		info.setVersion("1.0.0");

		val server = new Server();
		server.setUrl("https://www.tiki-sopt.p-e.kr");

		return new OpenAPI()
			.components(components)
			.security(List.of(securityRequirement))
			.servers(List.of(server))
			.info(info);
	}
}
