package com.neusoft.busManager.test;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.service.IBusDriverService;

public class TestBusDriver {

	public static void main(String[] args) throws Exception {
	ApplicationContext ac=new ClassPathXmlApplicationContext("context.xml");
	  IBusDriverService ibds=ac.getBean("BusDriverService",IBusDriverService.class);
	  BusDriverModel bdm=new BusDriverModel();
	  //测试司机信息的增加功能
	  bdm.setDriverid(1811);
	  bdm.setDname("吴师傅");
	  bdm.setSex("男");
	  bdm.setAge(35);
	  bdm.setBirthday(new Date());
	  bdm.setDcard("44142419967776099");
	  bdm.setDcode("12367899");
	  bdm.setJoindate(new Date());
	  bdm.setLeavedate(new Date());
	  ibds.add(bdm);
	  
	//测试司机信息的修改功能
//	  bdm.setDname("杨师傅");
//	  bdm.setDriverid("1001");
//	  ibds.modify(bdm);
	//测试司机信息的删除功能
//	  bdm.setDriverid("1001");
//	  ibds.delete(bdm);
	}

}
