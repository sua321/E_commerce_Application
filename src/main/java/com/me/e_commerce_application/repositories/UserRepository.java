package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}