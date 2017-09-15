package com.neusoft.busManager.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.queryinfo.model.BusIllegalFeeModel;
import com.neusoft.busManager.queryinfo.model.IllegalTypeModel;
import com.neusoft.busManager.queryinfo.service.IBusIllegalFeeService;

public class TestBusIllegalFee {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
		IBusIllegalFeeService ibfs=ac.getBean("BusIllegalFeeService",IBusIllegalFeeService.class);
		BusIllegalFeeModel bim=new BusIllegalFeeModel();
		
		//测试车辆违章信息的增加功能
//		bim.setFeeno(2);
//		BusModel bm=new BusModel();
//		bm.setBusid(5);
//		bim.setBus(bm);
//		BusDriverModel bdm=new BusDriverModel();
//		bdm.setDriverid(5);
//		bim.setBusdriver(bdm);
//		IllegalTypeModel  itm=new IllegalTypeModel();
//		itm.setTypeno(5);
//		bim.setIllegaltype(itm);
//		bim.setIlleagaldate(new Date());
//		bim.setPaydate(new Date());
//		bim.setPayfee(1000);
//		bim.setPaysocore(3);
//		bim.setFeedesc("闯红灯");
//		ibfs.add(bim);
		
	//测试车辆违章信息的修改功能
//     	bim.setFeeno(2);
//     	bim.setPayfee(2000);
//     	ibfs.modify(bim);
		
		//测试车辆违章信息的删除功能
		bim.setFeeno(2);
		ibfs.delete(bim);
	}
     
}
