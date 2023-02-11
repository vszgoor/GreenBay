package com.example.greenbay.services;

import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.dtos.SellableItemDetailedDTO;
import com.example.greenbay.dtos.SellableItemShortDTO;
import java.util.List;

public interface ItemService {
  CreateItemDTO save(CreateItemDTO createItemDTO, String sellerName);

  List<SellableItemShortDTO> listItems(Integer pageNumber);

  SellableItemDetailedDTO getItem(Long itemID);
}
