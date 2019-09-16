package com.zx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.domain.User;
import com.zx.mapper.loginMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	loginMapper lgMapper;
	
	@Override
	public User checkUserLogin(User user) {
		User rntUser = lgMapper.selectByLogin(user);
		
		return rntUser;
	}

	@Override
	public boolean checkUserName(String username) {
		User rntUser = lgMapper.selectByUsername(username);
		
		return rntUser != null;
	}

	
}
