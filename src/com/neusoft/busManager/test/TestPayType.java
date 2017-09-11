package com.neusoft.busManager.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.feeinfo.model.PayTypeModel;
import com.neusoft.busManager.feeinfo.service.IPayTypeService;

public class TestPayType {

	@Test
	public void testInsert() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IPayTypeService payTypeService = ac.getBean("PayTypeService", IPayTypeService.class);

		PayTypeModel ptm = new PayTypeModel();
		ptm.setTypename("一年停");
		ptm.setPayto("lili");
		ptm.setPayfee(4444);
		payTypeService.add(ptm);
	}

	@Test
	public void testUpdate() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IPayTypeService payTypeService = ac.getBean("PayTypeService", IPayTypeService.class);

		PayTypeModel ptm = new PayTypeModel();
		ptm.setTypeno(1);
		ptm.setTypename("asd年停");
		ptm.setPayto("lili");
		ptm.setPayfee(4444);
		payTypeService.modify(ptm);
	}

	@Test
	public void testDelete() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");

		IPayTypeService payTypeService = ac.getBean("PayTypeService", IPayTypeService.class);

		PayTypeModel ptm = new PayTypeModel();
		ptm.setTypeno(1);

		payTypeService.delete(ptm);
	}

	@Test
	public void testSelect() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		IPayTypeService payTypeService = ac.getBean("PayTypeService", IPayTypeService.class);
		PayTypeModel ptm = payTypeService.get(2);
		System.out.println(ptm.getTypename());
	}

}
