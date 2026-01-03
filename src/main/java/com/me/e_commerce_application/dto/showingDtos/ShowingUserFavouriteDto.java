package com.me.e_commerce_application.dto.showingDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ShowingUserFavouriteDto {
    String userId;
    String ItemId;
}
