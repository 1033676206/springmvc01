package com.zx.service;

import com.zx.domain.User;

public interface UserService {

	User checkUserLogin(User user);
	
	boolean checkUserName(String username);
}
