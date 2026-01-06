package com.me.e_commerce_application.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // 1.stateless sessions(token-based authentication)
        // 2.disable CSRF(to improve performance)
        //Authorize HTTP Request
       return http
                // 1.stateless sessions(token-based authentication)
                .sessionManagement(
                        c-> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 2.disable CSRF(to improve performance)
                .csrf(AbstractHttpConfigurer::disable) // or .csrf(c-> c.disable())


                //Authorize HTTP Request
                .authorizeHttpRequests(c-> c
                        .requestMatchers("/item/**").permitAll()
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .anyRequest().authenticated()
                ).build();
    }
}
