package com.me.e_commerce_application;

import com.me.e_commerce_application.repositories.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);

        UsersRepository usersRepository = context.getBean(UsersRepository.class);
//
//        usersRepository.save(
//                Users.builder()
//                        .userName("Lol")
//                        .userType("admin")
//                        .build()
//
//        );
    }



}
