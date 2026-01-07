package com.me.e_commerce_application.services;

import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserComments;
import com.me.e_commerce_application.dto.fetchingDtos.FetchingUserFavouriteDto;
import com.me.e_commerce_application.dto.showingDtos.*;
import com.me.e_commerce_application.models.Users;
import com.me.e_commerce_application.models.other_dependencies.UserCart;
import com.me.e_commerce_application.models.other_dependencies.UserComments;
import com.me.e_commerce_application.models.other_dependencies.UserFavourite;
import com.me.e_commerce_application.models.sub_dependencies.UserCredentials;
import com.me.e_commerce_application.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class UserInAppService {
    private final UsersRepository usersRepository;
    private final UserCartRepository userCartRepository;
    private final UsersFavouriteRepository usersFavouriteRepository;
    private final UsersCommentsRepository usersCommentsRepository;
    private final UsersCredentialsRepository usersCredentialsRepository;

    // List of the cart for display
    public List<ShowingUserCartShortDto> showUserCart(String id){
        List<ShowingUserCartShortDto> shortCart = new ArrayList<>();
        List<UserCart> userCart = userCartRepository.findAllByUsersId(id);
        // mapping to showingUserCartShortDto

        for(UserCart cart : userCart){
            ItemFullDto item = ItemService.showOneItem(cart.getItemId());
            // mapping
           shortCart.add(
                   ShowingUserCartShortDto.builder()
                           .userId(cart.getUsers().getId())
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
        Users users = usersRepository.findById(userId).orElseThrow();
        UserCart userCart = userCartRepository.findByUsersIdAndItemId(userId, itemId);
        ItemFullDto item = ItemService.showOneItem(itemId);
        // mapping
        return  ShowingUserCartFullDto.builder()
                .userId(users.getId())
                .userName(users.getUserName())
                .addedDateAndTime(userCart.getDateTime())
                .itemId(item.itemId)
                .itemImage(item.showCaseImage) // this should not be showcase image rather image of item users added to the cart
                .count(item.stock)
                .vendor(item.vendor)
                .manufacture(item.manufacture)
                .price(item.price)
                .availability(item.availability)
                .build();
    }

    public List<FetchingUserFavouriteDto> fetchingAllUserFavourite(String userId){
        List<UserFavourite> userFavourite = usersFavouriteRepository.findAll();
        List<FetchingUserFavouriteDto> FetchingUserFavouriteDtos = new ArrayList<>();
        for(UserFavourite favourite : userFavourite){
            FetchingUserFavouriteDtos.add(
                    FetchingUserFavouriteDto.builder()
                            .userId(favourite.getUserId())
                            .ItemId(favourite.getItemId())
                            .build()
            );
        }

        return FetchingUserFavouriteDtos;
    }

//    public ItemFullDto showingOneUserFavourite(String userId, String ItemId){
//        return new ItemFullDto();
//    }

    public List<FetchingUserComments> showingAllUserComment(String userId) {

        return new ArrayList<>();
    }

    public List<FetchingUserComments> showingOneUserComment(String userId, String ItemId) {
        Users users = usersRepository.findById(userId).orElseThrow();
        List<UserComments> userComments = usersCommentsRepository.findAllByUsersIdAndItemId(userId, ItemId);
        List<FetchingUserComments> comments = new ArrayList<>();
        for (UserComments userComment : userComments) {
            comments.add(FetchingUserComments.builder()
                    .id(userComment.getId())
                    .userId(users.getId())
                    .itemId(userComment.getItemId())
                    .userName(users.getUserName())
                    .comment(userComment.getComment())
                    .date(userComment.getDateTime())
                    .build());

        }
        return comments;
    }
    //UserProfile
    public UserProfileDto showingUserProfile(String userId){
        Users users = usersRepository.findById(userId).orElseThrow();
        UserCredentials userCredentials = usersCredentialsRepository.findById(userId).orElseThrow(); // need to be secure(need to check is the jwt token valid)
        // mapping
        return UserProfileDto.builder() //need to add profile pic,first and lastname
                .id(users.getId())
                .userName(users.getUserName())
                .email(userCredentials.getEmail())
                .userType(users.getUserType())
                .phoneNumbers(users.getPhoneNumbers())
                .addresses(users.getAddresses())
                .build();
    }
}
