package com.me.e_commerce_application.daos.userDaos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.me.e_commerce_application.validator.custom_annotations.AgeVarification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegistrationDao(
        @NotBlank(message = "Email is required")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Username is required")
        @JsonProperty("userName")
        String userName,

        @NotBlank(message = "Password is required")
        @JsonProperty("password")
        String password,

        @NotBlank(message = "UserType is required")
        @JsonProperty("userType")
        String userType,

        @JsonProperty("fullName")
        String fullName,

        @NotBlank(message = "Date of birth required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in format yyyy-MM-dd")
        @AgeVarification(value = 18, message = "You must be 18+ to register")
        @JsonProperty("DOB")
        String DOB) {
    // phone number is optional
}
