package com.me.e_commerce_application.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }
}
