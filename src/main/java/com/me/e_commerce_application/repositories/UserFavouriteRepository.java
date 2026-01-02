package com.me.e_commerce_application.repositories;

import com.me.e_commerce_application.models.other_dependencies.UserFavourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavouriteRepository extends JpaRepository<UserFavourite, String> {
}