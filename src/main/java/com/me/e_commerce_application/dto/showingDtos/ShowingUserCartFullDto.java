package com.me.e_commerce_application.dto.showingDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
@AllArgsConstructor
@Builder
public class ShowingUserCartFullDto {
    // this is bit different from Item fullDto bcs this will include user's name and order count and other details
    String userId;
    String userName;
    LocalDateTime addedDateAndTime;
    String itemId;
    String itemImage; //showcasing image
    int count;
    String vendor;
    String manufacture;
    String price;
    boolean availability;

}
