package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findUserByUserName(String userName);
}