package com.me.e_commerce_application.dto.showingDtos;

import com.me.e_commerce_application.models.sub_dependencies.Address;
import com.me.e_commerce_application.models.sub_dependencies.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
@Builder
@AllArgsConstructor
public class UserProfileDto {
    String id;
    String userName;
//    String profilePicture;
//    String firstName;
//    String lastName;
    String email;
    String userType;
    List<PhoneNumber> phoneNumbers;
    List<Address> addresses;

}
