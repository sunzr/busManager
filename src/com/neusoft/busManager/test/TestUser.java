package com.neusoft.busManager.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.admin.model.UserModel;
import com.neusoft.busManager.admin.service.IUserService;


public class TestUser {
	

	
	public static void main(String[] args) throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
		
		IUserService userService=ac.getBean("UserService", IUserService.class);
		
		
		
/*		UserModel user=new UserModel();
		
		user.setUserid("111");
		user.setPassword("123");
		user.setName("牛嘟嘟");
		userService.add(user);
		delete(userService);
		update(userService);*/
		
		get(userService);
	}
	
	public static void delete(IUserService userService) throws Exception {
		UserModel user=new UserModel();
		user.setUserid("112");
		userService.delete(user);
	}
	
	public static void update(IUserService userService) throws Exception {
		UserModel um=new UserModel();
		um.setUserid("111");
		um.setName("牛嘟嘟2");
		um.setPassword("123");
		userService.modify(um);
	}
	
	public static void get(IUserService userService) throws Exception {

		UserModel userModel = userService.get("111");
		System.out.println(userModel.getName());
	}
	
}
