package com.me.e_commerce_application.dto.showingDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
@AllArgsConstructor
@Builder
public class ShowingUserCartShortDto { // lists of cart items
    String userId;
    String itemId;
    String image; //showcasing Image
    String price;
    String vendor;
    LocalDateTime addedDateAndTime;
    int count; // purchase count
}
