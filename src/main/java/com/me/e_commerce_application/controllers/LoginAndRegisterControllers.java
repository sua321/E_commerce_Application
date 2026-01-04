package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.daos.userDaos.UserLoginDao;
import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.services.UserRegistrationAndLoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor

@RestController
public class LoginAndRegisterControllers {
   private final UserRegistrationAndLoginService userRegistrationAndLoginService;

    @PostMapping
    public String  userRegistration(UserRegistrationDao userRegistrationDao){
        return userRegistrationAndLoginService.registeringCustomerOrVendor(userRegistrationDao);
    }

    @PostMapping
    public String userLogin(UserLoginDao userLoginDao){
       return userRegistrationAndLoginService.userLogin(userLoginDao);
    }
}
