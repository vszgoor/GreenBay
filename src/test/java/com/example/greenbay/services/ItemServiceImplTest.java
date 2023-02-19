package com.example.greenbay.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.dtos.SellableItemShortDTO;
import com.example.greenbay.models.Item;
import com.example.greenbay.models.User;
import com.example.greenbay.repositories.BidRepository;
import com.example.greenbay.repositories.ItemRepository;
import com.example.greenbay.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

class ItemServiceImplTest {

  private ItemRepository itemRepository;
  private UserRepository userRepository;
  private BidRepository bidRepository;

  private ItemService itemService;

  @BeforeEach
  void setUp() {
    itemRepository = Mockito.mock(ItemRepository.class);
    userRepository = Mockito.mock(UserRepository.class);
    bidRepository = Mockito.mock(BidRepository.class);

    itemService = new ItemServiceImpl(itemRepository, userRepository, bidRepository);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void saveShouldSaveTheItem() {
    //ARRANGE
    CreateItemDTO createItemDTO = new CreateItemDTO("testItem", "testDescription",
        "https://test.com", 10.0, 100.0);
    String sellerName = Mockito.anyString();
    Mockito.when(userRepository.findByUsername(sellerName)).thenReturn(Optional.of(new User()));

    //ACT
    CreateItemDTO createdItemDTO = itemService.save(createItemDTO, sellerName);

    //ASSERT
    assertNotNull(createdItemDTO);
    Mockito.verify(itemRepository, Mockito.times(1)).save(Mockito.any());
    assertEquals(createItemDTO.getName(), createdItemDTO.getName());

  }

  @Test
  void listItemsShouldListItemsPerPage() {

    //ARRANGE
    List<Item> listOfItems = new ArrayList<>();
    listOfItems.add(new Item("test1", "desc1", "https://test.com", 10.0, 100.0, new User()));
    listOfItems.add(new Item("test2", "desc2", "https://test.com", 10.0, 100.0, new User()));
    listOfItems.add(new Item("test3", "desc3", "https://test.com", 10.0, 100.0, new User()));

    Page mockListOfItems = new PageImpl(listOfItems);

    Mockito.when(itemRepository.findSellableItemsByPage(Mockito.any())).thenReturn(mockListOfItems);

    //ACT
    List<SellableItemShortDTO> listOfItemDTOs = itemService.listItems(Mockito.any());

    //ASSERT
    assertNotNull(listOfItemDTOs);
    assertEquals(3, listOfItemDTOs.size());
    Mockito.verify(itemRepository, Mockito.times(1)).findSellableItemsByPage(Mockito.any());
  }
}