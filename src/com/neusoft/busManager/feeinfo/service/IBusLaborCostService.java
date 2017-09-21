package com.neusoft.busManager.feeinfo.service;

import java.util.List;

import com.neusoft.busManager.feeinfo.model.BusLaborCostModel;

//车辆缴费的业务层
public interface IBusLaborCostService {
	// 增加车辆缴费
	public void add(BusLaborCostModel blcm) throws Exception;

	// 修改车辆缴费
	public void modify(BusLaborCostModel blcm) throws Exception;

	// 删除车辆缴费
	public void delete(BusLaborCostModel blcm) throws Exception;

	//
	public BusLaborCostModel get(int costno) throws Exception;

	// 取得所有车辆缴费列表
	public List<BusLaborCostModel> getListByAll() throws Exception;

	// 取得所有车辆缴费列表,分页模式
	public List<BusLaborCostModel> getListByAllWithPage(int rows, int page) throws Exception;

	// 取得所有车辆缴费个数
	public int getCountByAll() throws Exception;

	// 取得所有车辆缴费页数
	public int getPageCountByAll(int rows) throws Exception;

	// 检查指定的ID是否存在，用于增加新车辆缴费时检查
	public boolean checkBusLaborCostExist(String BusLaborCostid) throws Exception;

	// 检查指定的车辆缴费名称是否存在，用于增加新车辆缴费时检查
	public boolean checkNameExist(String name) throws Exception;

	// 检查指定的车辆缴费是否可以删除，通过判断是否有外键关联
	public boolean checkCanDelete(int costno) throws Exception;

	public int getCountByCondition(int costno, int busid, int driverid) throws Exception;

	public List<BusLaborCostModel> getListByConditionWithPage(int typeno, int busid, int driverid, int rows, int page)
			throws Exception;

	public int getPageCountByCondition(int typeno, int busid, int driverid, int rows) throws Exception;
}
