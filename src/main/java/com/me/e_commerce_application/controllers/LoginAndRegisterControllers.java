package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.daos.userDaos.UserLoginDao;
import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.services.UserRegistrationAndLoginService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

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
    public String userLogin(@RequestBody @Valid UserLoginDao userLoginDao){
        // Determine if the user provided a username or an email
        String principal = null;
        if (userLoginDao.userName() != null && !userLoginDao.userName().isBlank()) {
            principal = userLoginDao.userName();
        } else if (userLoginDao.email() != null && !userLoginDao.email().isBlank()) {
            principal = userLoginDao.email();
        } else {
            return "Login Failed: Username or Email is required";
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(principal, userLoginDao.password())
            );
            return "Login Successful";
        } catch (AuthenticationException e) {
            return "Login Failed: Username or Password is wrong";
        }
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
