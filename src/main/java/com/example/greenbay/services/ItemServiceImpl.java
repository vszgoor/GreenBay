package com.example.greenbay.services;

import com.example.greenbay.dtos.BidDTO;
import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.dtos.SellableItemDetailedDTO;
import com.example.greenbay.dtos.SellableItemShortDTO;
import com.example.greenbay.exceptions.GreenBayException;
import com.example.greenbay.models.Bid;
import com.example.greenbay.models.Item;
import com.example.greenbay.models.User;
import com.example.greenbay.repositories.BidRepository;
import com.example.greenbay.repositories.ItemRepository;
import com.example.greenbay.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  private final UserRepository userRepository;

  private final BidRepository bidRepository;

  private ModelMapper modelMapper;

  private final Integer numberOfItemsPerPage = 20;


  public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository,
                         BidRepository bidRepository) {
    this.itemRepository = itemRepository;
    this.userRepository = userRepository;
    this.bidRepository = bidRepository;
    modelMapper = new ModelMapper();
  }


  @Override
  public CreateItemDTO save(CreateItemDTO createItemDTO, String sellerName) {
    Item item = new Item();
    modelMapper.map(createItemDTO, item);
    User seller = userRepository.findByUsername(sellerName).orElseThrow();
    item.setSeller(seller);
    seller.addItemToSell(item);
    itemRepository.save(item);

    CreateItemDTO createdItem = new CreateItemDTO();
    modelMapper.map(item, createdItem);

    return createdItem;
  }

  @Override
  public List<SellableItemShortDTO> listItems(Integer pageNumber) {
    Page<Item> listOfItems;
    if (pageNumber == null) {
      listOfItems = itemRepository.findSellableItemsByPage(
          PageRequest.of(0, numberOfItemsPerPage));
    } else {
      listOfItems = itemRepository.findSellableItemsByPage(
          PageRequest.of(pageNumber - 1, numberOfItemsPerPage));
    }

    List<SellableItemShortDTO> listOfItemDTOs = new ArrayList<>();
    for (Item item : listOfItems.getContent()) {
      SellableItemShortDTO sellableItemShortDTO = new SellableItemShortDTO();
      modelMapper.map(item, sellableItemShortDTO);
      listOfItemDTOs.add(sellableItemShortDTO);
    }

    return listOfItemDTOs;
  }

  @Override
  public SellableItemDetailedDTO getItem(Long itemID) {
    var item = itemRepository.findById(itemID)
        .orElseThrow(() -> new GreenBayException("Item with ID " + itemID + " is not found"));

    return itemToSellableItemDetailedDTO(item);
  }

  @Override
  public SellableItemDetailedDTO bidding(Long itemID, Double bidAmount, String bidderName) {

    User bidder = userRepository.findByUsername(bidderName).orElseThrow();

    biddingValidation(itemID, bidAmount, bidder);

    Item item = itemRepository.findById(itemID).get();

    placeBid(item, bidAmount, bidder);

    if (bidAmount >= item.getPurchasePrice()) {
      buyItem(item, bidAmount, bidder);
    }

    return itemToSellableItemDetailedDTO(item);
  }


  private void biddingValidation(Long itemID, Double bidAmount, User bidder) {

    if (itemRepository.findById(itemID).isEmpty()) {
      throw new GreenBayException("Item is not found");
    }

    Item item = itemRepository.findById(itemID).get();

    if (!item.getSellable()) {
      throw new GreenBayException("Item can't be bought");
    }

    if (item.getSeller() == bidder) {
      throw new GreenBayException("The seller can't buy their own item");
    }

    if (bidder.getBalance() < bidAmount) {
      throw new GreenBayException("There's not enough money on the user's account");
    }

    if (bidAmount < item.getStartingPrice()) {
      throw new GreenBayException("The bid is too low");
    }

    if (item.getLastBidAmount() != null &&
        bidAmount <= item.getLastBidAmount()) {
      throw new GreenBayException("The bid is too low");
    }
  }

  private void placeBid(Item item, Double bidAmount, User bidder) {
    Bid bid = new Bid(bidAmount, bidder, item);
    item.addBid(bid);
    bidder.addBid(bid);

    bidRepository.save(bid);
  }

  private SellableItemDetailedDTO itemToSellableItemDetailedDTO(Item item) {
    SellableItemDetailedDTO itemDTO = new SellableItemDetailedDTO();
    modelMapper.map(item, itemDTO);
    itemDTO.setSellerName(item.getSeller().getUsername());
    itemDTO.setBuyerName(item.getBuyer() != null ? item.getBuyer().getUsername() : null);

    List<BidDTO> bidDTOs = new ArrayList<>();
    for (Bid bid : item.getBids()) {
      BidDTO bidDTO = new BidDTO(bid.getAmount(), bid.getBidder().getUsername());
      bidDTOs.add(bidDTO);
    }
    itemDTO.setBids(bidDTOs);

    return itemDTO;
  }

  private void buyItem(Item item, Double bidAmount, User bidder) {
    item.setBuyer(bidder);
    item.setSellable(false);
    bidder.setBalance(bidder.getBalance() - bidAmount);
    itemRepository.save(item);
  }

}
