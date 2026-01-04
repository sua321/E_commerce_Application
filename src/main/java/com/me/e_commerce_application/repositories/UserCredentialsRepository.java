package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String> {
    UserCredentials findUserCredentialsByEmail(String email);
}