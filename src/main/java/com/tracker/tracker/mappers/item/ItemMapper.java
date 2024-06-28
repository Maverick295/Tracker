package com.tracker.tracker.mappers.item;

import com.tracker.tracker.dto.item.ItemDTO;
import com.tracker.tracker.entities.Item;
import com.tracker.tracker.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<ItemDTO, Item> {
    private final ModelMapper modelMapper;

    @Autowired
    public ItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public Item mapTo(ItemDTO source) {
        return modelMapper.map(source, Item.class);
    }

    @Override
    public ItemDTO mapFrom(Item destination) {
        return modelMapper.map(destination, ItemDTO.class);
    }
}
