package com.me.e_commerce_application.dto.showingDtos;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AllCommentsDto(String userId, String userName, String comment, LocalDateTime date) {
}
