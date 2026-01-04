package com.me.e_commerce_application.daos.userDaos;

public record UserRegistrationDao(String email ,String username, String password,String userType, String fullName, String DOB) {
    // phone number is optional
}
