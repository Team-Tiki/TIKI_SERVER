package com.tiki.server.common.config;

import com.tiki.server.common.support.RedisRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = "com.tiki.server",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = RedisRepository.class
        )
)
public class JpaAuditingConfig {
}
