package com.me.e_commerce_application.daos.userDaos;

import com.me.e_commerce_application.validator.custom_annotations.EmailCheck;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDao(@EmailCheck
                           String email,
                           String userName,
                           @NotBlank(message = "Password is required")
                           String password) {
}
