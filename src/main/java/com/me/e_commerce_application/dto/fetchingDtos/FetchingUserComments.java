package com.me.e_commerce_application.dto.fetchingDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class FetchingUserComments {
    String id;
    String userId;
    String itemId;
    String userName;
    String comment;
    LocalDateTime date; // commented date and time
}
