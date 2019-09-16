package com.zx.HandlerInterceptor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheck implements HandlerInterceptor {
	
	static Set<String> memo = new HashSet<>();
	static {
		Collections.synchronizedCollection(memo);
		memo.add("ajaxUsername.action");
		memo.add("ajaxPassword.action");
		memo.add("login.action");
	}
	
	private String url;
	
	@Override
	public boolean preHandle(HttpServletRequest res, HttpServletResponse resq, Object obj) throws Exception {
		
		HttpSession session = res.getSession();
		String address = res.getRequestURI();
		url = address.substring(address.lastIndexOf("/") + 1);
		System.out.println("url: " + url);
		
		String s =(String) session.getAttribute("userMess");
		if (s != null)// 直接放行
			return true;
		
		// 判断路径名，ajax请求以及登录请求都放行
		if (memo.contains(url)) {
			return true;
		}
		
		if (session.getAttribute("userMess") == null) {
			String username = res.getParameter("username");
			String password = res.getParameter("password");
			
			if (username != null && password != null) {
				session.setAttribute("userMess", "ok");
				System.out.println("username: " + username + '\n' + "password: " + password);
				return true;
			}
		}
		
		System.out.println("进行拦截!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		res.getRequestDispatcher("login.action").forward(res, resq);
		
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest res, HttpServletResponse resp, Object obj, Exception exception)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest res, HttpServletResponse resp, Object obj, ModelAndView modelAndView)
			throws Exception {
		System.out.println("处理器执行后，返回视图前");
	}

}
/*
 * <property name="memo">
					<set>
						<value>ajaxUsername.action</value>
						<value>ajaxPassword.action</value>
						<value>login.action</value>
					</set>
				</property>
				*/
