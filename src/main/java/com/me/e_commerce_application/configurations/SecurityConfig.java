package com.me.e_commerce_application.configurations;

import com.me.e_commerce_application.services.UserRegistrationAndLoginService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserRegistrationAndLoginService userRegistrationAndLoginService;
//    private final PasswordEncoder passwordEncoder; // for now i dont need to set the userDetails service and the passWord Encoder manually spring will do it automatically

//    //Registering DAOAuthenticationProvider
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        var provider = new DaoAuthenticationProvider(userRegistrationAndLoginService);
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }


    //Registering DAOAuthenticationProvider as the default implementation of AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


    //Authorizing URL endpoints
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
                        .requestMatchers(HttpMethod.POST,"/user/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/user/validate").permitAll()
                        .anyRequest().authenticated()
                ).build();
    }
}
