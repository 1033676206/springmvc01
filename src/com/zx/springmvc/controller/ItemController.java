package com.zx.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zx.domain.Item;
import com.zx.domain.Qu;
import com.zx.domain.User;
import com.zx.service.ItemService;

@Controller // 交给spring管理
public class ItemController {
	
	@Autowired
	ItemService itemservie;// 将servie层的实现注入进来

	@RequestMapping("/itemList.action")// http映射地址
	public ModelAndView queryItemList() {
		// 创建页面需要显示的商品数据
		/*
			List<Item> list = new ArrayList<>();
			list.add(new Item(1, "1华为 荣耀8", 2399, new Date(), "质量好！1"));
			list.add(new Item(2, "2华为 荣耀8", 2399, new Date(), "质量好！2"));
			list.add(new Item(3, "3华为 荣耀8", 2399, new Date(), "质量好！3"));
			list.add(new Item(4, "4华为 荣耀8", 2399, new Date(), "质量好！4"));
			list.add(new Item(5, "5华为 荣耀8", 2399, new Date(), "质量好！5"));
			list.add(new Item(6, "6华为 荣耀8", 2399, new Date(), "质量好！6"));
			*/

			// 创建ModelAndView，用来存放数据和视图
			ModelAndView modelAndView = new ModelAndView();
			List<Item> list = itemservie.selectAllItem();
			// 设置数据到模型中
			modelAndView.addObject("itemList", list);
			// 设置视图jsp，需要设置视图的物理地址
			modelAndView.setViewName("itemList");

			return modelAndView;

	}
	
	@RequestMapping("/itemEdit.action")
	public ModelAndView editItemList(int id) {// 参数还可以通过HttpServletRequest参数来获取getParameter
		
		Item item = itemservie.editItemById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item",item);
		modelAndView.setViewName("editItem");
		
		return modelAndView;
	}
	
	@RequestMapping("/updateitem.action")
	public ModelAndView updateItemList(Item item,MultipartFile pictureFile) throws IOException {// 参数绑定
		//Item item = new Item(id,name,price,detail,null);
		//Item item = qu.getItem();
		//pictureFile.transferTo(new File("D:\\SpringProject\\springmvc01\\WebContent\\pic\\小鸟.png"));
		//System.out.println(item);
		
		// 图片名字: 随机UUID + '.' + 后缀名
		String firstName = UUID.randomUUID().toString();// UUID
		// 获取文件名,再通过文件名拿到后缀获取文件类型
		String oriName = pictureFile.getOriginalFilename();
		String lastName = oriName.substring(oriName.lastIndexOf('.'));
		
		String picName = firstName + lastName;
		
		item.setPic(picName);// 将地址传给数据库
		pictureFile.transferTo(new File("D:\\SpringProject\\springmvc01\\WebContent\\pic\\" + picName));
		
		ModelAndView modelAndView = new ModelAndView();
		itemservie.updateItemById(item);
		
		modelAndView.setViewName("seccuss");
		return modelAndView;
	}
	
}
