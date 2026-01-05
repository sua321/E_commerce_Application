package com.me.e_commerce_application.daos.userDaos;

import com.me.e_commerce_application.validator.custom_annotations.AgeVarification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegistrationDao(
        @NotBlank(message = "Email is required") String email,
        @NotBlank(message = "Username is required") String userName,
        @NotBlank(message = "Password is required") String password,
        String userType,
        String fullName,
        @NotBlank(message = "Date of birth required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in format yyyy-MM-dd")
        @AgeVarification(value = 18, message = "You must be 18+ to register")
        String DOB) {
    // phone number is optional
}
