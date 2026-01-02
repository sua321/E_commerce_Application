package com.me.e_commerce_application.dto;

import com.me.e_commerce_application.daos.ItemDetailsDao;
import lombok.Getter;

@Getter
public class ItemIdDto {
    private final String itemId;
    public ItemIdDto(ItemDetailsDao itemDetailsDao){
        this.itemId = itemDetailsDao.itemId();
    }
}
