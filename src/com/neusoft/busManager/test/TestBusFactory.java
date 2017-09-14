package com.neusoft.busManager.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusFactoryModel;
import com.neusoft.busManager.baseinfo.service.IBusFactoryService;

public class TestBusFactory {

	public static void main(String[] args)throws Exception {
	   ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
	   IBusFactoryService ibfs=ac.getBean("BusFactoryService",IBusFactoryService.class);
	   
	   BusFactoryModel bfm=new   BusFactoryModel();
	   //测试车辆厂家的增加功能
	   bfm.setFactoryno(1);
	   bfm.setFactoryname("东风一号");
	   bfm.setFactorydesc("生产各类货车");
	   ibfs.add(bfm);
	   
	   //测试车辆厂家的修改功能
//	   bfm.setFactoryno(1);
//	   bfm.setFactoryname("五菱汽车有限公司");
//	   bfm.setFactorydesc("生产中国神车五菱汽车");
//	   ibfs.modify(bfm);
	   
	   //测试车辆厂家的删除功能
//	    bfm.setFactoryno(1);
//	    ibfs.delete(bfm);
	}

}
