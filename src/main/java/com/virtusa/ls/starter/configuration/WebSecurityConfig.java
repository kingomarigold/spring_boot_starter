package com.virtusa.ls.starter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * 
 * Security for configuratione.
 *
 *
 * @author Karthik.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    /**
     * 
     * @param HttpSecurity.
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        .addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/login")
            .permitAll()
            .anyRequest()
            .authenticated();
        });
        return http.build();
    }

    /**
     * 
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("user")
                .password("{noop}password")
                .authorities("app")
                .build()
        );
    }

}
