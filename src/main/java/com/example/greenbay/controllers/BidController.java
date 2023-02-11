//package com.example.greenbay.controllers;
//
//import com.example.greenbay.dtos.BidDTO;
//import com.example.greenbay.dtos.SellableItemDetailedDTO;
//import com.example.greenbay.services.BidService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//
//@RestController
//public class BidController {
//
//    final
//    BidService bidService;
//
//    public BidController(BidService bidService) {
//        this.bidService = bidService;
//    }
//
//    @PostMapping("/bidding")
//    public ResponseEntity bidding(@RequestBody
//                                  BidDTO bidDetailsForSellableItemDTO, Principal principal){
//        String username = principal.getName();
//        SellableItemDetailedDTO sellableItemDetailedDTO = bidService.bidding(
//            bidDetailsForSellableItemDTO, username);
//        return ResponseEntity.status(201).body(sellableItemDetailedDTO);
//    }
//}
