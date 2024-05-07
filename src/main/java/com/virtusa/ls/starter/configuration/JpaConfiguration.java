package com.virtusa.ls.starter.configuration;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * 
 * Configuration for JPA.
 *
 *
 * @author Karthik
 */
@Configuration
@EnableJpaRepositories("com.virtusa.ls")
@EnableJpaAuditing
public class JpaConfiguration {
    @Bean
    public AuditorAware<UUID> auditorProvider() {
      return new AuditorAwareImpl();
    }
}
