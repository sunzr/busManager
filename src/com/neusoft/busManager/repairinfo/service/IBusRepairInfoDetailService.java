package com.neusoft.busManager.repairinfo.service;

import java.util.List;


import com.neusoft.busManager.repairinfo.model.BusRepairInfoDetailModel;

public interface IBusRepairInfoDetailService {
	//增加车辆维修明细表
	public void add(BusRepairInfoDetailModel model) throws Exception;
	//修改车辆维修明细表
	public void modify(BusRepairInfoDetailModel model) throws Exception;
	//删除车辆维修明细表
	public void delete(BusRepairInfoDetailModel model) throws Exception;
	//得到指定的车辆维修明细表
	public BusRepairInfoDetailModel get(int DetailNo) throws Exception;
	//得到所有的车辆维修明细表列表
	public List<BusRepairInfoDetailModel> getListByAll() throws Exception;
	//用分页得到所有的车辆维修明细表列表
	public List<BusRepairInfoDetailModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得所有车辆维修明细表的页数
	public int getPageCountByAll(int rows) throws Exception;
	//得到所有的车辆维修明细表个数
	public int getCountByAll() throws Exception;
}
