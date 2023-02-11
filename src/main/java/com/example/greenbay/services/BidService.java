package com.example.greenbay.services;

import com.example.greenbay.dtos.BidDTO;
import com.example.greenbay.dtos.SellableItemDetailedDTO;

public interface BidService {
  SellableItemDetailedDTO bidding(BidDTO bidDetailsForSellableItemDTO, String username);
}
