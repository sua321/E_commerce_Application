package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.other_dependencies.UserComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersCommentsRepository extends JpaRepository<UserComments, String> {
    List<UserComments> findAllByUsersIdAndItemId(String userId, String itemId);
}