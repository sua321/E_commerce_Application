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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserInAppService {
    private final UsersRepository usersRepository;

    private final UserCartRepository userCartRepository;
    private final UsersFavouriteRepository usersFavouriteRepository;
    private final UsersCommentsRepository usersCommentsRepository;
    private final UsersCredentialsRepository usersCredentialsRepository;
    private final Pattern emailPattern;


    // getting current user's principle(username or email)
    String gettingUserId(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
             throw new NullPointerException("Authentication is null");
        }
        String identifier = (String)authentication.getPrincipal();
        if(isItEmail(identifier)){
            return usersCredentialsRepository.findUsersCredentialsByEmail(identifier).getId();
        }
        return usersRepository.findUserByUserName(identifier).getId();
    }

    //is identifier user email or id
    private boolean isItEmail(String identifier){

        return emailPattern.matcher(identifier).matches();
    }

    // List of the cart for display
    public List<ShowingUserCartShortDto> showUserCart() {
        String id = gettingUserId();
        List<ShowingUserCartShortDto> shortCart = new ArrayList<>();
        List<UserCart> userCart = userCartRepository.findAllByUsersId(id);
        // mapping to showingUserCartShortDto

        for (UserCart cart : userCart) {
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
            );
        }
        return shortCart;
    }

    // individual item of cart for display
    public ShowingUserCartFullDto showSpecificItemInCart( String itemId) {
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
        String userId = gettingUserId();
        if (userId == null){
            return null;
        }
        Users users = usersRepository.findById(userId).orElseThrow();
        UserCart userCart = userCartRepository.findByUsersIdAndItemId(userId, itemId);
        if (userCart == null){
            System.out.println("userCart is null");
            return null;
        }
        ItemFullDto item = ItemService.showOneItem(itemId);
        // mapping
        return ShowingUserCartFullDto.builder()
                .userId(users.getId())
                .userName(users.getUserName())
                .addedDateAndTime(userCart.getDateTime()) // important: throws null pointer exception
                .itemId(item.itemId)
                .itemImage(item.showCaseImage) // this should not be showcase image rather image of item users added to the cart
                .count(item.stock)
                .vendor(item.vendor)
                .manufacture(item.manufacture)
                .price(item.price)
                .availability(item.availability)
                .build();
    }
    @Transactional
    public List<FetchingUserFavouriteDto> fetchingAllUserFavourite() {
        String userId = gettingUserId();
        if (userId == null){
            return null;
        }
        List<UserFavourite> userFavourite = usersFavouriteRepository.findAllByUsersId(userId);
        List<FetchingUserFavouriteDto> FetchingUserFavouriteDtos = new ArrayList<>();
        for (UserFavourite favourite : userFavourite) {
            FetchingUserFavouriteDtos.add(
                    FetchingUserFavouriteDto.builder()
                            .userId(favourite.getUsers().getId())
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

    public List<FetchingUserComments> showingOneUserComment(String ItemId) {
        String userId = gettingUserId();
        if (userId == null){
            return null;
        }
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
    @Transactional
    public UserProfileDto showingUserProfile() {
        String userId = gettingUserId();
        if (userId == null)
            return null;
        Users user = usersRepository.findById(userId).orElseThrow();
        UserCredentials userCredentials = usersCredentialsRepository.findById(userId).orElseThrow();



        // Mapping & Initializing Collections
        // We wrap them in new ArrayList<>(...) to force Hibernate to fetch the data NOW while the transaction is open.
        return UserProfileDto.builder() //need to add profile pic,first and lastname
                .id(user.getId())
                .userName(user.getUserName())
                .email(userCredentials.getEmail())
                .fullName(user.getFullName())
                .userType(user.getUserType())
                .phoneNumbers(new ArrayList<>(user.getPhoneNumbers()))
                .addresses(new ArrayList<>(user.getAddresses()))
                .build();


    }
}
