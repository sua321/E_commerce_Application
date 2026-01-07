package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserComments;
import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserFavouriteDto;
import com.me.e_commerce_application.dto.showingDtos.*;
import com.me.e_commerce_application.services.UserInAppService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@AllArgsConstructor

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserInAppService userInAppService;

    @PostMapping("/userCart/{id}")
    public List<ShowingUserCartShortDto> showUserCart(@PathVariable String  id) {
        // I use  the SavingUserCartDto is for example, but it has different purpose(saving in db)
        return userInAppService.showUserCart(id);
    }
        // these itemId and userId will be come from frontend
    public ShowingUserCartFullDto showSpecificItemInCart(String userId, String itemId) {
        return userInAppService.showSpecificItemInCart(userId, itemId);
    }
//Users Profile
    public UserProfileDto showingUserProfile(String userId){
        return userInAppService.showingUserProfile(userId);
    }

    //Note: i can use React use effect for fetching this alongside with other http request
    //Users Favourite
    public List<FetchingUserFavouriteDto> fetchingAllUserFavourite(String userId) {
        return userInAppService.fetchingAllUserFavourite(userId);
    }

//    public ItemFullDto showingOneUserFavourite(String userId, String ItemId) {
//        return userService.showingOneUserFavourite(userId, ItemId);
//    }

    //Users comments
//    public List<FetchingUserComments> showingAllUserComment(String userId) {
//        return userService.showingAllUserComment(userId);
//    }

    public List<FetchingUserComments> showingOneUserComment(String userId, String ItemId) {
//        Note: i can use React use effect for fetching this alongside with other http request
        return userInAppService.showingOneUserComment(userId, ItemId);
    }
}