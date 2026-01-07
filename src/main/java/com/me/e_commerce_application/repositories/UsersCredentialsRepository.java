package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersCredentialsRepository extends JpaRepository<UserCredentials, String> {
    UserCredentials findUsersCredentialsByEmail(String email);
}