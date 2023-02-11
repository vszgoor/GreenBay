package com.example.greenbay.services;

import com.example.greenbay.dtos.BidDTO;
import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.dtos.SellableItemDetailedDTO;
import com.example.greenbay.dtos.SellableItemShortDTO;
import com.example.greenbay.exceptions.GreenBayException;
import com.example.greenbay.models.Bid;
import com.example.greenbay.models.Item;
import com.example.greenbay.models.User;
import com.example.greenbay.repositories.ItemRepository;
import com.example.greenbay.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;

  private final UserRepository userRepository;

  private ModelMapper modelMapper;

  private final Integer numberOfItemsPerPage = 3;


  public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
    this.itemRepository = itemRepository;
    this.userRepository = userRepository;
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
      listOfItems = itemRepository.findSellableItemsByPage(PageRequest.of(0, numberOfItemsPerPage));
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
        .orElseThrow(() -> new GreenBayException("Item with ID " + itemID + "is not found"));

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
}
