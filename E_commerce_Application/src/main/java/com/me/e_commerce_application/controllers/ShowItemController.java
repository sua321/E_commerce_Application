package com.me.e_commerce_application.controllers;

import com.me.e_commerce_application.daos.itemDaos.ItemShortDetailsDao;
import com.me.e_commerce_application.dto.showingDtos.ItemFullDto;
import com.me.e_commerce_application.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/item")
public class ShowItemController {
    private final ItemService itemService;

    @GetMapping("/allItems")
    public List<ItemShortDetailsDao> getAllItems(){
        return itemService.showAllItems();
    }
    @GetMapping("/oneItem/{:id}")
    public ItemFullDto getOneItem(@PathVariable String  id){
        return ItemService.showOneItem(id);
    }
}