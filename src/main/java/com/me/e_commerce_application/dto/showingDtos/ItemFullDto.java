package com.me.e_commerce_application.dto.showingDtos;

import java.util.List;

public class ItemFullDto { // for showing Individual items
    String itemId;
    String itemName;
    String showCaseImage;
    List<String> image;      // Image
    String vendor;
    String category;
    String manufacture;
    String description;
    String price;
    int stock;
    boolean availability;
}
