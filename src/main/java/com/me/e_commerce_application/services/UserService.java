package com.me.e_commerce_application.services;

import com.me.e_commerce_application.dto.showingDtos.*;
import com.me.e_commerce_application.models.other_dependencies.UserCart;
import com.me.e_commerce_application.repositories.UserCartRepository;
import com.me.e_commerce_application.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCartRepository userCartRepository;

    public List<ShowingUserCartShortDto> showUserCart(String id){
        List<UserCart> userCart = userCartRepository.findAllByUserId(id);
        return new ArrayList<>();
    }

    public ShowingUserCartFullDto showSpecificItemInCart(String userId, String itemId){
        return new ShowingUserCartFullDto();
    }

    public List<ShowingUserFavouriteDto> showingAllUserFavourite(String userId){
        return new ArrayList<>();
    }

    public ItemFullDto showingOneUserFavourite(String userId, String ItemId){
        return new ItemFullDto();
    }

    public List<ShowingUserComments> showingAllUserComment(String userId) {
        return new ArrayList<>();
    }

    public ShowingUserComments showingOneUserComment(String userId, String ItemId) {
        return new ShowingUserComments();
    }

}
