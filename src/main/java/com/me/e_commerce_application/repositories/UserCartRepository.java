package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.other_dependencies.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCartRepository extends JpaRepository<UserCart, String> {
    List<UserCart> findAllByUserId(String userId);

    UserCart findByUserIdAndItemId(String userId, String itemId);

}