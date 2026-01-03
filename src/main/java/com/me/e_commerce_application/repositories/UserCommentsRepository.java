package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.other_dependencies.UserComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommentsRepository extends JpaRepository<UserComments, String> {
    List<UserComments> findAllByUserIdAndItemId(String userId, String itemId);
}