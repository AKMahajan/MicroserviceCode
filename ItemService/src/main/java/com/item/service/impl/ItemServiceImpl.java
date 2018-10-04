package com.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.domain.Item;
import com.item.repository.ItemRepository;
import com.item.service.ItemService;

@Service
public class ItemServiceImpl  implements ItemService{
	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<Item> getItems() {
		return (List<Item>)itemRepository.findAll();
		
	}

	@Override
	public Item getItemByName(String itemName) {
		return itemRepository.findByName(itemName);
		
	}

}
