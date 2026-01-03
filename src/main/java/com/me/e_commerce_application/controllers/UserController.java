package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.dto.savingDtos.SavingUserCartDto;
import com.me.e_commerce_application.dto.showingDtos.*;
import com.me.e_commerce_application.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor

@RestController
public class UserController {
    private final UserService userService;

    public List<ShowingUserCartShortDto> showUserCart(String id) {
        // I use  the SavingUserCartDto is for example, but it has different purpose(saving in db)
        return userService.showUserCart(id);
    }
        // these itemId and userId will be come from frontend
    public ShowingUserCartFullDto showSpecificItemInCart(String userId, String itemId) {
        return userService.showSpecificItemInCart(userId, itemId);
    }

    //Note: i have to modify all the plans that are below(for user profile)
    //User Favourite
    public List<ShowingUserFavouriteDto> showingAllUserFavourite(String userId) {
        return userService.showingAllUserFavourite(userId);
    }

    public ItemFullDto showingOneUserFavourite(String userId, String ItemId) {
        return userService.showingOneUserFavourite(userId, ItemId);
    }

    //User comments
    public List<ShowingUserComments> showingAllUserComment(String userId) {
        return userService.showingAllUserComment(userId);
    }

    public ShowingUserComments showingOneUserComment(String userId, String ItemId) {
        return userService.showingOneUserComment(userId, ItemId);
    }
}