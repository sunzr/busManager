package com.neusoft.busManager.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.admin.model.FunctionModel;
import com.neusoft.busManager.admin.service.IFunctionService;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.queryinfo.model.BusOilInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusOilInfoService;



public class TestBusOil {
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
		
		IBusOilInfoService service=ac.getBean("busOilInfoService", IBusOilInfoService.class);
		
//		BusOilInfoModel busoil=new BusOilInfoModel();
//		BusModel bus=new BusModel();
//		bus.setBusid("1001");
//		busoil.setBus(bus);
//		busoil.setBusmile(50);
//		busoil.setInfodate(new Date());
//		busoil.setOilfee(100);
//		busoil.setOilvolume(100);
//		
//		BusDriverModel driver=new BusDriverModel();
//		driver.setDriverid("1001");
//		busoil.setDriver(driver);
		
//		service.insert(busoil);
		
		BusOilInfoModel busoil = service.select(1);
		System.out.println(busoil.getOilfee());
		System.out.println(busoil.getBus().getBusname());
		
	}
	
}
