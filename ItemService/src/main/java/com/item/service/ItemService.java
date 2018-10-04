package com.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.item.domain.Item;
@Service
public interface ItemService {
	public List<Item> getItems();
	public Item getItemByName(String itemName);

}
