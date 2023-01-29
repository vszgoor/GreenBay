package com.example.greenbay.services;

import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.models.Item;

public interface ItemService {
    CreateItemDTO save(CreateItemDTO createItemDTO);
}
