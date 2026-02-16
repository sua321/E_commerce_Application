package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.other_dependencies.UserFavourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersFavouriteRepository extends JpaRepository<UserFavourite, String> {
    List<UserFavourite> findAllByUsersId(String userId);
}