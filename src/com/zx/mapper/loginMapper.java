package com.zx.mapper;

import com.zx.domain.User;

public interface loginMapper {

	// 通过账号和密码进行查询
	User selectByLogin(User user);
	
	// 通过账号进行查询
	User selectByUsername(String username);
}
