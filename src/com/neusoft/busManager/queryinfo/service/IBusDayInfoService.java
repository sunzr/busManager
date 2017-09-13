package com.neusoft.busManager.queryinfo.service;

import java.util.List;

import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;

//车辆日运行信息的业务接口
public interface IBusDayInfoService {
	 //增加车辆日运行信息
		public void add(BusDayInfoModel bim)throws Exception;
		//修改车辆日运行信息
		public void modify(BusDayInfoModel bim)throws Exception;
		//删除车辆日运行信息
		public void delete(BusDayInfoModel bim)throws Exception;
		//按序号取得指定的车辆日运行信息
		public BusDayInfoModel get(int infono)throws Exception;
		//取得所有的车辆日运行信息
		public List<BusDayInfoModel> getListByAll()throws Exception;
		
		//按车辆取得车辆日运行信息
		public List<BusDayInfoModel> getListByBus(String busid)throws Exception;
		
		//按司机对象取得车辆日运行信息
		public List<BusDayInfoModel> getListByBusDriver(String driverid)throws Exception;
		
		//按检索条件取得所有车辆日运行信息
		   public List<BusDayInfoModel> gettListByCondition(String busid,String driverid) throws Exception;
		//分页方式取得所有车辆日运行信息
		public List<BusDayInfoModel> getListByAllWithPage(int rows,int page)throws Exception;
		
		//按检索条件取得所有车辆日运行信息，分页方式
		public List<BusDayInfoModel> getListByConditionWithPage(String busid,String driverid,int rows,int page) throws Exception;		
		//取得车辆日运行信息的个数
		public int getCountByAll()throws Exception;
		
		//按检索条件取得车辆日运行信息的个数
		   public int getCountByCondition(String busid,String driverid) throws Exception;
		//按检索条件取得车辆日运行信息的页数
		   public int getPageCountByCondition(String busid,String driverid,int rows) throws Exception;
}
