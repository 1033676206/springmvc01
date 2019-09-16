package com.zx.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.zx.domain.Item;
import com.zx.mapper.itemMapper;

@Service("itemServiceImpl")
public class ItemServiceImpl implements ItemService {

	@Autowired
	itemMapper mapper;

	@Override
	public List<Item> selectAllItem() {
		
		List<Item> list = mapper.selectAllItem();
		return list;
	}
	
	@Override
	public Item editItemById(Integer id) {
		Item item = mapper.selectItemById(id);
		return item;
	}
	
	@Test
	public void demoTest() {
		/*
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml","spring/applicationContext-service.xml");
		SqlSessionFactory sessionFactory =(SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
		SqlSession session = sessionFactory.openSession();		
		itemMapper map = session.getMapper(itemMapper.class);
		List<Item> list = map.selectAllItem();
		for(int i = 0;i < list.size();i ++) {
			System.out.println(list.get(i));
		}
		*/
		
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml","spring/applicationContext-service.xml");
		ItemServiceImpl itemServiceImpl =(ItemServiceImpl) applicationContext.getBean("itemServiceImpl");
		List<Item> list = itemServiceImpl.selectAllItem();
		for(int i = 0;i < list.size();i ++) {
			System.out.println(list.get(i));
		}
		
		itemServiceImpl.mapper.selectAllItem();
		//mapper.selectAllItem();
	}

	@Override
	public void updateItemById(Item item) {
		mapper.updateItem(item);
	}
	
	/*
	private String name;
	
	@Value("天神")
	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
		ItemServiceImpl impl = (ItemServiceImpl)applicationContext.getBean("itemServiceImpl");
		System.out.println(impl.name);
	}
	*/


}
