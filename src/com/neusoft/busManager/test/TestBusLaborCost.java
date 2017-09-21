package com.neusoft.busManager.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.feeinfo.model.BusLaborCostModel;
import com.neusoft.busManager.feeinfo.service.IBusLaborCostService;

public class TestBusLaborCost {

	@Test
	public void testInsert() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);

		BusLaborCostModel blcm = new BusLaborCostModel();
		BusDriverModel bdm = new BusDriverModel();
		bdm.setDriverid(1001);
		blcm.setBusDriverModel(bdm);
		payTypeService.add(blcm);
	}

	@Test
	public void testUpdate() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);

		BusLaborCostModel blcm = new BusLaborCostModel();
		BusModel busModel = new BusModel();
		busModel.setBusid(1001);
		payTypeService.modify(blcm);
	}

	@Test
	public void testDelete() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);

		BusLaborCostModel bpfm = new BusLaborCostModel();
		payTypeService.delete(bpfm);
	}

	@Test
	public void testSelect() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);
		BusLaborCostModel blcm = payTypeService.get(3);
		System.out.println(blcm.getCostno());
	}

	@Test
	public void testGetListByAllWithPage() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);
		List<BusLaborCostModel> list = payTypeService.getListByAllWithPage(10, 1);
		for (BusLaborCostModel bpfm : list) {
		}
	}

	@Test
	public void testCheckNameExist() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);
	}

	@Test
	public void testGetCountByAll() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IBusLaborCostService payTypeService = ac.getBean("BusLaborCostService", IBusLaborCostService.class);
		System.out.println(payTypeService.getCountByAll());
	}

}
