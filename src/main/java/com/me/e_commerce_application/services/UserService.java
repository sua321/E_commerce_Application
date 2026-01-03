package com.me.e_commerce_application.services;

import com.me.e_commerce_application.dto.showingDtos.*;
import com.me.e_commerce_application.models.User;
import com.me.e_commerce_application.models.other_dependencies.UserCart;
import com.me.e_commerce_application.models.other_dependencies.UserFavourite;
import com.me.e_commerce_application.repositories.UserCartRepository;
import com.me.e_commerce_application.repositories.UserFavouriteRepository;
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
    private final UserFavouriteRepository userFavouriteRepository;

    // List of the cart for display
    public List<ShowingUserCartShortDto> showUserCart(String id){
        List<ShowingUserCartShortDto> shortCart = new ArrayList<>();
        List<UserCart> userCart = userCartRepository.findAllByUserId(id);
        // mapping to showingUserCartShortDto

        for(UserCart cart : userCart){
            ItemFullDto item = ItemService.showOneItem(cart.getItemId());
            // mapping
           shortCart.add(
                   ShowingUserCartShortDto.builder()
                           .userId(cart.getUser().getId())
                           .itemId(cart.getItemId())
                           .image(item.showCaseImage)
                           .price(item.price)
                           .vendor(item.vendor)
                           .addedDateAndTime(cart.getDateTime())
                           .count(item.stock)
                           .build()
           ) ;
        }
        return shortCart;
    }

// individual item of cart for display
    public ShowingUserCartFullDto showSpecificItemInCart(String userId, String itemId){
        /*
        * String userId;
    String userName;
    LocalDateTime addedDateAndTime;
    String itemId;
    String itemImage; //showcasing image
    int count;
    String vendor;
    String manufacture;
    String price;
    boolean availability;*/
        User user = userRepository.findById(userId).orElseThrow();
        UserCart userCart = userCartRepository.findByUserIdAndItemId(userId, itemId);
        ItemFullDto item = ItemService.showOneItem(itemId);
        // mapping
        return  ShowingUserCartFullDto.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .addedDateAndTime(userCart.getDateTime())
                .itemId(item.itemId)
                .itemImage(item.showCaseImage) // this should not be showcase image rather image of item user added to the cart
                .count(item.stock)
                .vendor(item.vendor)
                .manufacture(item.manufacture)
                .price(item.price)
                .availability(item.availability)
                .build();
    }

    public List<ShowingUserFavouriteDto> showingAllUserFavourite(String userId){
        // this should need show the user's favourite items in the profile
        // but i didnt made any decision about profile so now i will stick with what i thought before
        // just sending array of userFavourite Dtos
        List<UserFavourite> userFavourite = userFavouriteRepository.findAll();
        List<ShowingUserFavouriteDto> showingUserFavouriteDtos = new ArrayList<>();
        for(UserFavourite favourite : userFavourite){
            showingUserFavouriteDtos.add(
                    ShowingUserFavouriteDto.builder()
                            .userId(favourite.getUserId())
                            .ItemId(favourite.getItemId())
                            .build()
            );
        }

        return showingUserFavouriteDtos;
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
