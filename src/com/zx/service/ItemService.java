package com.zx.service;

import java.util.List;

import com.zx.domain.Item;

public interface ItemService {

	Item editItemById(Integer id);
	
	List<Item> selectAllItem();
	
	void updateItemById(Item item);
}
