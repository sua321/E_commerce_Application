package com.me.e_commerce_application.daos;

public record ItemShortDetailsDao(String itemId, String itemName, String category, String showcaseImage,
                                  String manufacture, String vendor, String price) {
}
