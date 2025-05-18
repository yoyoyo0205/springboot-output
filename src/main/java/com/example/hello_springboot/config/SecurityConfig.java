package com.example.hello_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**","/swagger-ui/**","/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
            )
        .httpBasic(Customizer.withDefaults()); // ← Basic認証を有効化

        return http.build();
    }
}
