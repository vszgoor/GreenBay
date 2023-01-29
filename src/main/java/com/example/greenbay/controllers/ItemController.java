package com.example.greenbay.controllers;

import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.models.Item;
import com.example.greenbay.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ItemController {

    final
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/item")
    public ResponseEntity addItem(@Valid @RequestBody CreateItemDTO createItemDTO) {
        return ResponseEntity.status(201).body(itemService.save(createItemDTO));
    }


}
