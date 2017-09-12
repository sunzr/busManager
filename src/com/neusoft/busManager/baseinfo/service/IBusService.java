package com.neusoft.busManager.baseinfo.service;

import java.util.List;

import com.neusoft.busManager.baseinfo.model.BusModel;

//车辆信息Service业务接口
public interface IBusService {
	     //增加车辆信息
			public void add(BusModel bm) throws Exception;
			 //修改车辆信息
			public void modify(BusModel bm) throws Exception;
			//删除车辆信息
			public void delete(BusModel bm) throws Exception;
			
			//取得指定的车辆信息
			 public BusModel get(String busid)throws Exception;
			//取得所有车辆信息
				public List<BusModel> getListByAll() throws Exception;
			//按车辆类型取得车辆信息
				public List<BusModel> getListByBusType(int typeno) throws Exception;
			//按车辆厂家编号取得车辆信息
				public List<BusModel> getListByBusFactory(int factoryno) throws Exception;	
			//按条件取得所有车辆信息
				public List<BusModel> getListByCondition(int typeno, int factoryno) throws Exception;
			//分页方式取得所有车辆信息
				public List<BusModel> getListByAllWithPage(int rows,int page)throws Exception;
				
				//按检索条件取得所有车辆信息，分页方式
				public List<BusModel> getListByConditionWithPage(int typeno,int factoryno,int rows,int page) throws Exception;		
			//取得车辆信息的个数
				public int getCountByAll() throws Exception;
				//按检索条件取得车辆信息的个数
				   public int getCountByCondition(int typeno,int factoryno) throws Exception;
				//按检索条件取得车辆信息的页数
			   public int getPageCountByCondition(int typeno,int factoryno,int rows) throws Exception;
}
