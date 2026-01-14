package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserComments;
import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserFavouriteDto;
import com.me.e_commerce_application.dto.showingDtos.*;
import com.me.e_commerce_application.services.UserInAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserInAppService userInAppService;

    @GetMapping("/userCart/{id}")
    public List<ShowingUserCartShortDto> showUserCart(@PathVariable String  id) {
        // I use  the SavingUserCartDto is for example, but it has different purpose(saving in db)
        return userInAppService.showUserCart(id);
    }
        // these itemId and userId will be come from frontend
    public ShowingUserCartFullDto showSpecificItemInCart(String userId, String itemId) {
        return userInAppService.showSpecificItemInCart(userId, itemId);
    }
//Users Profile
    @GetMapping("/profile")// getting user using SecurityContextHolder
    public ResponseEntity<UserProfileDto> showingUserProfile(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            return ResponseEntity.notFound().build();
        }
        var identifier = (String) authentication.getPrincipal();
            UserProfileDto userProfileDto = userInAppService.showingUserProfile(identifier);
            if (userProfileDto == null) {
//                System.out.println("User is null");
                return ResponseEntity.notFound().build();
            }
//        System.out.println("user is here : " + userProfileDto);
        return ResponseEntity.ok(userProfileDto);
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