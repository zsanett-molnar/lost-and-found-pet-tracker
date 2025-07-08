package com.project.pet_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Updated syntax
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()  // ✅ Public /login
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Optional: keep if using basic auth

        return http.build();
    }
}
