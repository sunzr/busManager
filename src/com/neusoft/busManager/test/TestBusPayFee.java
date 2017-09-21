package com.neusoft.busManager.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.feeinfo.model.BusPayFeeModel;
import com.neusoft.busManager.feeinfo.service.IBusPayFeeService;

public class TestBusPayFee {

	@Test
	public void testInsert() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);

		BusPayFeeModel ptm = new BusPayFeeModel();
		BusDriverModel bdm = new BusDriverModel();
		bdm.setDriverid(1001);
		ptm.setPayTo("liuxia");
		ptm.setBusDriverModel(bdm);
		payTypeService.add(ptm);
	}

	@Test
	public void testUpdate() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);

		BusPayFeeModel ptm = new BusPayFeeModel();
		BusModel busModel=new BusModel();
		ptm.setPayno(3);
		ptm.setPayAmount(500);
		busModel.setBusid(1001);
		payTypeService.modify(ptm);
	}

	@Test
	public void testDelete() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);

		BusPayFeeModel bpfm = new BusPayFeeModel();
		bpfm.setPayno(7);
		payTypeService.delete(bpfm);
	}

	@Test
	public void testSelect() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);
		BusPayFeeModel ptm = payTypeService.get(3);
		System.out.println(ptm.getPayno());
	}

	@Test
	public void testGetListByAllWithPage() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);
		List<BusPayFeeModel> list = payTypeService.getListByAllWithPage(10, 1);
		for (BusPayFeeModel bpfm : list) {
			System.out.println(bpfm.getPayAmount());
		}
	}

	@Test
	public void testCheckNameExist() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);
	}

	@Test
	public void testGetCountByAll() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusPayFeeService payTypeService = ac.getBean("BusPayFeeService", IBusPayFeeService.class);
		System.out.println(payTypeService.getCountByAll());
	}

}
