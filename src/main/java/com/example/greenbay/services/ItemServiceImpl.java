package com.example.greenbay.services;

import com.example.greenbay.dtos.CreateItemDTO;
import com.example.greenbay.models.Item;
import com.example.greenbay.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private ModelMapper modelMapper;


    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        modelMapper = new ModelMapper();
    }


    @Override
    public CreateItemDTO save(CreateItemDTO createItemDTO) {
        Item item = new Item();
        modelMapper.map(createItemDTO, item);
        itemRepository.save(item);
        return createItemDTO;
    }
}
