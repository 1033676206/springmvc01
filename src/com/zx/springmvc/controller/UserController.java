package com.zx.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zx.domain.User;
import com.zx.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userServiec;
	
	@RequestMapping("/login.action")
	public ModelAndView toLogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/ajaxUsername.action")
	public String checkUsername(String username) {
		System.out.println("username: " + username);
		// 根据名字查询
		boolean res = userServiec.checkUserName(username);
		String str = res ? "FFF" : "NNN";
		System.out.println("res: " + res);
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping("/ajaxPassword.action")
	public String checkPassword(User user) {
		User rntUser = userServiec.checkUserLogin(user);
		String str = rntUser != null ? "Yes" : "False";
		return str;
	}
	
	@RequestMapping("/userLogin.action")
	public ModelAndView userLogin(User user) {// 将传过来的用户名和密码用User进行参数绑定
		System.out.println("user: " + user);
		User rntUser = userServiec.checkUserLogin(user);
		System.out.println("查询后");
		System.out.println("rntUser: " + rntUser);
		String message;
		if (rntUser == null) 
			message = "登录失败";
		else {
			message = "欢迎" + user.getUsername();
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mess", message);
		modelAndView.setViewName("Main");
		return modelAndView;
	}
}
