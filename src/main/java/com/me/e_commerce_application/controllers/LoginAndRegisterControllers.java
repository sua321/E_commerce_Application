package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.daos.userDaos.UserLoginDao;
import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.services.UserRegistrationAndLoginService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor

@RestController
@RequestMapping("/user")
public class LoginAndRegisterControllers {
   private final UserRegistrationAndLoginService userRegistrationAndLoginService;
   private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String  userRegistration(@RequestBody @Valid UserRegistrationDao userRegistrationDao){
        return userRegistrationAndLoginService.registeringCustomerOrVendor(userRegistrationDao);
    }

    @PostMapping("/login")
    public void userLogin(@RequestBody @Valid UserLoginDao userLoginDao){
        authenticationManager.authenticate()
    }
}
