package com.neusoft.busManager.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.repairinfo.model.BusRepairInfoDetailModel;
import com.neusoft.busManager.repairinfo.model.BusRepairInfoModel;
import com.neusoft.busManager.repairinfo.service.IBusRepairInfoDetailService;

public class TestDetail {
	
	@Test
	public void Test01() throws Exception{
		ApplicationContext ac= new ClassPathXmlApplicationContext("context.xml");
		IBusRepairInfoDetailService bean = ac.getBean("BusRepairInfoDetailService",IBusRepairInfoDetailService.class);
		BusRepairInfoDetailModel model=new BusRepairInfoDetailModel();
		model.setDetailNo(3);
		model.setItem("123");
		model.setItemDesc("123123321");
		model.setItemQTY(15312);
		model.setItemUnitPrice(7020);
		model.setReapirInfo(new BusRepairInfoModel());
		bean.add(model);
	
	
	
	}
	@Test
	public void Test02() throws Exception{
		ApplicationContext ac= new ClassPathXmlApplicationContext("context.xml");
		IBusRepairInfoDetailService bean = ac.getBean("BusRepairInfoDetailService",IBusRepairInfoDetailService.class);
		BusRepairInfoDetailModel model=new BusRepairInfoDetailModel();
		model.setDetailNo(1);
		model.setItem("1234");
		model.setItemDesc("12344321");
		model.setItemQTY(15);
		model.setItemUnitPrice(7000);
		model.setReapirInfo(new BusRepairInfoModel());
		bean.modify(model);
		
	}
	@Test
	public void Test03() throws Exception{
		ApplicationContext ac= new ClassPathXmlApplicationContext("context.xml");
		IBusRepairInfoDetailService bean = ac.getBean("BusRepairInfoDetailService",IBusRepairInfoDetailService.class);
		BusRepairInfoDetailModel model=new BusRepairInfoDetailModel();
		
		model.setDetailNo(3);
//		model.setItem("1234");
//		model.setItemDesc("12344321");
//		model.setItemQTY(15);
//		model.setItemUnitPrice(7000);
//		model.setReapirInfo(new BusRepairInfoModel());
		bean.delete(model);
		
	}
	@Test
	public void Test04() throws Exception{
		ApplicationContext ac= new ClassPathXmlApplicationContext("context.xml");
		IBusRepairInfoDetailService bean = ac.getBean("BusRepairInfoDetailService",IBusRepairInfoDetailService.class);
		BusRepairInfoDetailModel model=new BusRepairInfoDetailModel();
		
//		model.setDetailNo(3);
//		model.setItem("1234");
//		model.setItemDesc("12344321");
//		model.setItemQTY(15);
//		model.setItemUnitPrice(7000);
//		model.setReapirInfo(new BusRepairInfoModel());
		//bean.get(2);
		System.out.println(bean.getListByAll().size());
	}
}
