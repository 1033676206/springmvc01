package com.zx.mapper;

import java.util.List;

import com.zx.domain.Item;

public interface itemMapper {

	Item selectItemById(Integer id);
	
	List<Item> selectAllItem();
	
	void updateItem(Item item);
}
