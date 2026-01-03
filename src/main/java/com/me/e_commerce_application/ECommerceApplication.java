package com.me.e_commerce_application;

import com.me.e_commerce_application.models.User;
import com.me.e_commerce_application.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);
//
//        userRepository.save(
//                User.builder()
//                        .userName("Lol")
//                        .userType("admin")
//                        .build()
//
//        );
    }



}
