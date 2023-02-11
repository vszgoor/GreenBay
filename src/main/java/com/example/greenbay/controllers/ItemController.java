package com.example.greenbay.controllers;

import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.dtos.SellableItemDetailedDTO;
import com.example.greenbay.dtos.SellableItemShortDTO;
import com.example.greenbay.exceptions.SimpleErrorDTO;
import com.example.greenbay.models.Item;
import com.example.greenbay.models.User;
import com.example.greenbay.services.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class ItemController {

  final
  ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping("/item")
  public ResponseEntity addItem(@Valid @RequestBody CreateItemDTO createItemDTO,
                                Principal principal) {
    String sellerName = principal.getName();
    return ResponseEntity.status(201).body(itemService.save(createItemDTO, sellerName));
  }

  @GetMapping("/items")
  public ResponseEntity listItems(@RequestParam(required = false, name = "n") Integer pageNumber) {
    if (pageNumber != null && pageNumber <= 0) {
      return ResponseEntity.badRequest().body(new SimpleErrorDTO("Page number is not correct"));
    }

    List<SellableItemShortDTO> listOfItems = itemService.listItems(pageNumber);
    return ResponseEntity.ok(listOfItems);
  }

  @GetMapping("item/{itemID}")
  public ResponseEntity getItem(@PathVariable Long itemID) {
    SellableItemDetailedDTO sellableItemDetailedDTO = itemService.getItem(itemID);
    return ResponseEntity.ok(sellableItemDetailedDTO);
  }

  @PostMapping("/bidding")
  public ResponseEntity bidding(@RequestParam Long itemID,
                                @RequestParam Double bidAmount,
                                Principal principal){
    String bidderName = principal.getName();
    SellableItemDetailedDTO sellableItemDetailedDTO = itemService.bidding(
        itemID, bidAmount, bidderName);
    return ResponseEntity.status(201).body(sellableItemDetailedDTO);
  }


}
