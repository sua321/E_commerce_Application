package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserComments;
import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserFavouriteDto;
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
//User Profile
    public UserProfileDto showingUserProfile(String userId){
        return userService.showingUserProfile(userId);
    }

    //Note: i can use React use effect for fetching this alongside with other http request
    //User Favourite
    public List<FetchingUserFavouriteDto> fetchingAllUserFavourite(String userId) {
        return userService.fetchingAllUserFavourite(userId);
    }

//    public ItemFullDto showingOneUserFavourite(String userId, String ItemId) {
//        return userService.showingOneUserFavourite(userId, ItemId);
//    }

    //User comments
//    public List<FetchingUserComments> showingAllUserComment(String userId) {
//        return userService.showingAllUserComment(userId);
//    }

    public List<FetchingUserComments> showingOneUserComment(String userId, String ItemId) {
//        Note: i can use React use effect for fetching this alongside with other http request
        return userService.showingOneUserComment(userId, ItemId);
    }
}