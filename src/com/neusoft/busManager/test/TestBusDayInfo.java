package com.neusoft.busManager.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusDayInfoService;

public class TestBusDayInfo {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
		IBusDayInfoService ibds=ac.getBean("BusDayInfoService",IBusDayInfoService.class);
		BusDayInfoModel bdim=new BusDayInfoModel();
		
		//测试车辆日运行信息的增加功能
		bdim.setInfono(2);
		BusModel bm=new BusModel();
		bm.setBusid(1002);
		bdim.setBus(bm);
		BusDriverModel bdm=new BusDriverModel();
		bdm.setDriverid(1002);
		bdim.setBusdriver(bdm);
		bdim.setInfodate(new Date());
		bdim.setIncome(5000.00);
		bdim.setMileage(120);
		bdim.setOilwear(20.00);
		bdim.setInfodesc("送货");
		ibds.add(bdim);
		
		//测试车辆日运行信息的修改功能
//		bdim.setInfono(2);
//		bdim.setIncome(7000.00);
//		ibds.modify(bdim);
		
		//测试车辆日运行信息的删除功能
//			bdim.setInfono(2);
//			ibds.delete(bdim);
	}

}
