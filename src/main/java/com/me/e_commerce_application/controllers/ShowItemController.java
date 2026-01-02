package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.daos.ItemDetailsDao;
import com.me.e_commerce_application.daos.ItemShortDetailsDao;
import com.me.e_commerce_application.dto.showingDtos.ItemFullDto;
import com.me.e_commerce_application.dto.showingDtos.ItemShortDto;
import com.me.e_commerce_application.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@RestController
public class ShowItemController {
    private final ItemService itemService;

    @GetMapping("/1")
    public List<ItemShortDetailsDao> getAllItems(){
        return itemService.showAllItems();
    }
    @GetMapping("/2")
    public ItemFullDto getOneItem(String id, ItemDetailsDao items){
        return itemService.showOneItem(id, items);
    }
}