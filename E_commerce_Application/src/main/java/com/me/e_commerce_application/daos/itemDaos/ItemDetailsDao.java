package com.me.e_commerce_application.daos.itemDaos;


public record ItemDetailsDao(String itemId, String itemName, String category, String showcaseImage, String images,
                             String manufacture, String vendor, String description, String price, int stock,
                             boolean availability) {
}
