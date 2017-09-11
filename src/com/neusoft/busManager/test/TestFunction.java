package com.neusoft.busManager.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.admin.model.FunctionModel;
import com.neusoft.busManager.admin.service.IFunctionService;



public class TestFunction {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
		
		IFunctionService userService=ac.getBean("FunctionService", IFunctionService.class);
		
		List<FunctionModel> listByAll = userService.getListByAll();
		
		for (FunctionModel functionModel : listByAll) {
			System.out.println(functionModel.getFunurl());
		}
	}
	
}
