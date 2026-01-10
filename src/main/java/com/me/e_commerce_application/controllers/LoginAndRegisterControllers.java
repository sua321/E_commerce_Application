package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.daos.userDaos.UserLoginDao;
import com.me.e_commerce_application.daos.userDaos.UserRegistrationDao;
import com.me.e_commerce_application.dto.JwtResponseDto;
import com.me.e_commerce_application.services.JWTService;
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
   private final JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?>  userRegistration(@RequestBody @Valid UserRegistrationDao userRegistrationDao){
        return userRegistrationAndLoginService.registeringCustomerOrVendor(userRegistrationDao);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid UserLoginDao userLoginDao){
        // Determine if the user provided a username or an email
        String principal = null;
        if ((userLoginDao.userName() != null && !userLoginDao.userName().isBlank()) && userLoginDao.email() == null) {
            principal = userLoginDao.userName();
        } else if ((userLoginDao.email() != null && !userLoginDao.email().isBlank()) && userLoginDao.userName() == null) {
            principal = userLoginDao.email();
        } else {
            return ResponseEntity.badRequest().body("Login Failed: Username or Email is required");
        }

        try {
            //Authentication process
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(principal, userLoginDao.password())
            );
            //JWT token generating process
            var token = jwtService.generateToken(principal);
            return ResponseEntity.ok(new JwtResponseDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed: Invalid username or password");
        }
    }
    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader){
        var token = authHeader.replace("Bearer ", "");
        System.out.println("Request Validated");
        return jwtService.validateToken(token);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
