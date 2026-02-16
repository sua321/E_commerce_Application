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

    @GetMapping("/userCart")
    public ResponseEntity<List<ShowingUserCartShortDto>> showUserCart() {
        var cart = userInAppService.showUserCart();
        if (cart == null){
            return ResponseEntity.notFound().build();
        }
                return ResponseEntity.ok(cart);
    }
        // these itemId and userId will be come from frontend
    @GetMapping("/userCart/{itemId}")
    public ResponseEntity<ShowingUserCartFullDto> showSpecificItemInCart( @PathVariable String itemId) {
        var oneItem = userInAppService.showSpecificItemInCart(itemId);
        if(oneItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oneItem);
    }
//Users Profile
    @GetMapping("/profile")// getting user using SecurityContextHolder
    public ResponseEntity<UserProfileDto> showingUserProfile(){

            UserProfileDto userProfileDto = userInAppService.showingUserProfile();
        if (userProfileDto == null){
            return ResponseEntity.notFound().build();
        }

//        System.out.println("user is here : " + userProfileDto);
        return ResponseEntity.ok(userProfileDto);
    }

    //Note: i can use React use effect for fetching this alongside with other http request
    //Users Favourite
    @GetMapping("/userFavourite")
    public ResponseEntity<List<FetchingUserFavouriteDto>> fetchingAllUserFavourite() {
        var userFavourite = userInAppService.fetchingAllUserFavourite();
        if (userFavourite == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userFavourite);
    }


    //Users comments
//    public List<FetchingUserComments> showingAllUserComment(String userId) {
//        return userInAppService.showingAllUserComment(userId);
//    }
    @GetMapping("/userComment/{ItemId}")
    public ResponseEntity<List<FetchingUserComments>> showingOneUserComment(@PathVariable String ItemId) {
//        Note: i can use React use effect for fetching this alongside with other http request
        var userComments = userInAppService.showingOneUserComment(ItemId);
        if (userComments == null){
            return ResponseEntity.notFound().build();
        }

//        System.out.println("user is here : " + userProfileDto);
        return ResponseEntity.ok(userComments);
    }
}