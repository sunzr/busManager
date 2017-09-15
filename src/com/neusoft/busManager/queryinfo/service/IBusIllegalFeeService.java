package com.neusoft.busManager.queryinfo.service;

import java.util.List;

import com.neusoft.busManager.queryinfo.model.BusIllegalFeeModel;
//车辆违章信息业务接口
public interface IBusIllegalFeeService {
	//增加车辆违章信息
		public void add(BusIllegalFeeModel bif)throws Exception;
		//增加车辆违章信息
			public void modify(BusIllegalFeeModel bif)throws Exception;
		//增加车辆违章信息
		public void delete(BusIllegalFeeModel bif)throws Exception;
		
		//取得指定的车辆违章信息
		public BusIllegalFeeModel get(int feeno)throws Exception;
		
		//取得所有的车辆违章信息
		public List<BusIllegalFeeModel> getListByAll()throws Exception;
		
		//按车辆编号取得车辆违章信息
			public List<BusIllegalFeeModel> getListByBus(int busid)throws Exception;
		//按司机编号取得车辆违章信息
				public List<BusIllegalFeeModel> getListByBusDriver(int driverid)throws Exception;
		//按违章类型取得车辆违章信息
		public List<BusIllegalFeeModel> getListByIllgealType(int typeno)throws Exception;
		//按条件取得所有车辆违章信息
		public List<BusIllegalFeeModel> getListByCondition(int busid, int driverid, int typeno) throws Exception;
		//分页方式取得所有车辆违章信息
		public List<BusIllegalFeeModel> getListByAllWithPage(int rows,int page)throws Exception;
		
		//按检索条件取得所有车辆违章信息，分页方式
		public List<BusIllegalFeeModel> getListByConditionWithPage(int busid, int driverid, int typeno,int rows,int page) throws Exception;
		//取得车辆违章信息的个数
		public int getCountByAll() throws Exception;
		//按检索条件取得车辆违章信息的个数
		 public int getCountByCondition(int busid, int driverid, int typeno) throws Exception;
		//按检索条件取得车辆信息的页数
		   public int getPageCountByCondition(int busid, int driverid, int typeno,int rows) throws Exception;
}
