package com.me.e_commerce_application.services;

import com.me.e_commerce_application.daos.itemDaos.ItemShortDetailsDao;
import com.me.e_commerce_application.dto.showingDtos.ItemFullDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ItemService {

    private final RestClient restClient;

    public ItemService(RestClient.Builder builder, @Value("${external.api.url}") String externalApiUrl) {
        this.restClient = builder.baseUrl(externalApiUrl).build();
    }

    public List<ItemShortDetailsDao> showAllItems(){

        // TODO: Map itemShortDetailsDaos to List<ItemShortDto>
        return restClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<ItemShortDetailsDao>>() {});
    }

    public static ItemFullDto showOneItem(String id){
        // logic
        return new ItemFullDto();
    }
}
