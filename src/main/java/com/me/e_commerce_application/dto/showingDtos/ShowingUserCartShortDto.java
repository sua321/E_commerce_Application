package com.me.e_commerce_application.dto.showingDtos;

import java.time.LocalDateTime;

public class ShowingUserCartShortDto { // lists of cart items
    String userId;
    String itemId;
    String image; //showcasing Image
    String price;
    String vendor;
    LocalDateTime addedDateAndTime;
    int count; // purchase count
}
