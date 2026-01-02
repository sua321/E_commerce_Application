package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.other_dependencies.UserComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentsRepository extends JpaRepository<UserComments, String> {
}