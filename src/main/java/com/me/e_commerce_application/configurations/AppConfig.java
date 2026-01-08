package com.me.e_commerce_application.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestClient;

import java.util.regex.Pattern;

@Configuration
public class AppConfig {
    //Allowed Email Pattern
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

// Security config
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Email Pattern check
    @Bean
    public Pattern regexPattern(){

        return  Pattern.compile(EMAIL_REGEX);
    }
}



