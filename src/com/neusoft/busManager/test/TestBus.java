package com.neusoft.busManager.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusFactoryModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.baseinfo.model.BusTypeModel;
import com.neusoft.busManager.baseinfo.service.IBusService;

public class TestBus {

	public static void main(String[] args)throws Exception{
	ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
      IBusService ibs=ac.getBean("BusService",IBusService.class);
      BusModel bm=new BusModel();
        
      //测试车辆信息的增加功能
        bm.setBusid(1001);
        bm.setBuscardid("EB234");
        bm.setVinno("2");
        BusTypeModel btm=new BusTypeModel();
        btm.setTypeno(2);
        bm.setBustype(btm);
        BusFactoryModel bfm=new BusFactoryModel();
        bfm.setFactoryno(2);
        bm.setBusfactory(bfm);
        bm.setOutput(6.0);
        bm.setSeating(7);
        bm.setTotalmileage(15000);
        bm.setBussize("长8米，宽2米，高1.5米");
        bm.setOilwear(3.2);
        bm.setWload(5.6);
        ibs.add(bm);
        
      // 测试车辆信息的修改功能      
//      bm.setBusid("1002");
//    bm.setBuscardid("EB133");
//    bm.setVinno("3");
//    BusTypeModel btm=new BusTypeModel();
//    btm.setTypeno(3);
//    bm.setBustype(btm);
//    BusFactoryModel bfm=new BusFactoryModel();
//    bfm.setFactoryno(3);
//    bm.setBusfactory(bfm);
//    bm.setOutput(9.0);
//    bm.setSeating(8);
//    bm.setTotalmileage(16788);
//    bm.setBussize("长6米，宽2米，高1.5米");
//    bm.setOilwear(2.3);
//    bm.setWload(4.5);
//    ibs.modify(bm);
       
      // 测试车辆信息的删除功能  
//      bm.setBusid("1002");
//      ibs.delete(bm);
	}

}
