package com.neusoft.busManager.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusTypeModel;
import com.neusoft.busManager.baseinfo.service.IBusTypeService;

public class TestBusType {
	public static void main(String[] args) throws Exception{
		ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
		 IBusTypeService  ibts=ac.getBean("BusTypeService", IBusTypeService.class);
		 
		 BusTypeModel btm=new BusTypeModel();
		   //增加车辆类型
		    btm.setTypeno(1);
		    btm.setTypename("货车");
		    ibts.add(btm);
		 //删除车辆类型
//		   btm.setTypeno(1);
//		    ibts.delete(btm);
		    
		    //修改车辆类型
//		    btm.setTypeno(1);
//		    btm.setTypename("出租车");
//		    ibts.modify(btm);
	} 

}
