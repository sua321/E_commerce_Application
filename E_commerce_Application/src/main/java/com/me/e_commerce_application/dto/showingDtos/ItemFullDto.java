package com.me.e_commerce_application.dto.showingDtos;

import java.util.List;

public class ItemFullDto { // for showing Individual items
    public String itemId;
    public String itemName;
    public String showCaseImage;
    public List<String> image;      // Image
    public String vendor;
    public String category;
    public String manufacture;
    public String description;
    public String price;
    public int stock;
    public boolean availability;
}
