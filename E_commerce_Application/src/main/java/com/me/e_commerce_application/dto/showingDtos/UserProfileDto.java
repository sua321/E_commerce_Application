package com.me.e_commerce_application.dto.showingDtos;

import com.me.e_commerce_application.models.sub_dependencies.Address;
import com.me.e_commerce_application.models.sub_dependencies.PhoneNumber;
import lombok.Builder;

import java.util.List;

@Builder
public record UserProfileDto(
        String id,
        String userName,
        String email,
        String fullName,
        String userType,
        List<PhoneNumber> phoneNumbers,
        List<Address> addresses
) {}
