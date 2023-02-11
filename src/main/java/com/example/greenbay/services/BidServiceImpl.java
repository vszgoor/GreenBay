//package com.example.greenbay.services;
//
//import com.example.greenbay.dtos.BidDTO;
//import com.example.greenbay.dtos.SellableItemDetailedDTO;
//import com.example.greenbay.exceptions.GreenBayException;
//import com.example.greenbay.models.Bid;
//import com.example.greenbay.models.Item;
//import com.example.greenbay.models.User;
//import com.example.greenbay.repositories.BidRepository;
//import com.example.greenbay.repositories.ItemRepository;
//import com.example.greenbay.repositories.UserRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BidServiceImpl implements BidService {
//
//    private final BidRepository bidRepository;
//
//    private final ItemRepository itemRepository;
//
//    private final UserRepository userRepository;
//
//    private ModelMapper modelMapper;
//
//    public BidServiceImpl(BidRepository bidRepository, ItemRepository itemRepository, UserRepository userRepository) {
//        this.bidRepository = bidRepository;
//        this.itemRepository = itemRepository;
//        this.userRepository = userRepository;
//        this.modelMapper = new ModelMapper();
//    }
//
//    @Override
//    public SellableItemDetailedDTO bidding(
//        BidDTO bidDetailsForSellableItemDTO, String username) {
//
//        User bidder = userRepository.findByUsername(username).orElseThrow();
//
//        biddingValidation(bidDetailsForSellableItemDTO, bidder);
//
//        Item item = itemRepository.findById(bidDetailsForSellableItemDTO.getItemID()).get();
//
//        if (bidDetailsForSellableItemDTO.getAmount() < item.getPurchasePrice()) {
//
//            Bid bid = new Bid(bidDetailsForSellableItemDTO.getAmount(), bidder, item);
//            bidder.addBid(bid);
//            item.addBid(bid);
//
//            bidRepository.save(bid);
//            itemRepository.save(item);
//            userRepository.save(bidder);
//
//            SellableItemDetailedDTO sellableItemDetailedDTO = new SellableItemDetailedDTO();
//            modelMapper.map(item, sellableItemDetailedDTO);
//            return sellableItemDetailedDTO;
//        }
//
//        Bid bid = new Bid(bidDetailsForSellableItemDTO.getAmount(), bidder, item);
//        bidder.addBid(bid);
//        item.addBid(bid);
//        item.setBuyer(bidder);
//        bidder.addItemBought(item);
//        bidRepository.save(bid);
//        itemRepository.save(item);
//        userRepository.save(bidder);
//        SellableItemDetailedDTO sellableItemDetailedDTO = new SellableItemDetailedDTO();
//        modelMapper.map(item, sellableItemDetailedDTO);
//
//        return sellableItemDetailedDTO;
//    }
//
//
//    private void biddingValidation(BidDTO bidDetailsForSellableItemDTO, User bidder) {
//        Long itemID = bidDetailsForSellableItemDTO.getItemID();
//
//        if (itemRepository.findById(itemID).isEmpty()) {
//            throw new GreenBayException("Item is not found");
//        }
//
//        Item item = itemRepository.findById(itemID).get();
//
//        if (!item.getSellable()) {
//            throw new GreenBayException("Item can't be bought");
//        }
//
//        if(item.getSeller() == bidder) {
//            throw new GreenBayException("The seller can't buy their own item");
//        }
//
//        if (bidder.getBalance() < bidDetailsForSellableItemDTO.getAmount()) {
//            throw new GreenBayException("There's not enough money on the user's account");
//        }
//
//        if (item.getBids().isEmpty() && bidDetailsForSellableItemDTO.getAmount() < item.getStartingPrice()) {
//            throw new GreenBayException("The bid is too low");
//        }
//
//        if (!item.getBids().isEmpty() && bidDetailsForSellableItemDTO.getAmount() < item.getBids().get(item.getBids().size() - 1).getAmount()) {
//            throw new GreenBayException("The bid is too low");
//        }
//    }
//}
