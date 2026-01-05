package com.me.e_commerce_application.daos.userDaos;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDao(String email,
                           String userName,
                           @NotBlank(message = "Password is required")
                           String password) {
}
