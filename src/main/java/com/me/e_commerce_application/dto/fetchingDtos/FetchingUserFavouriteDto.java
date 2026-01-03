package com.me.e_commerce_application.dto.fetchingDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class FetchingUserFavouriteDto {
    String userId;
    String ItemId;
}
