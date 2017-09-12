package com.neusoft.busManager.repairinfo.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.repairinfo.model.RepairProviderModel;

public interface IRepairProviderService {
	//增加维修单位
	public void add(RepairProviderModel model) throws Exception;
	//修改维修单位
	public void modify(RepairProviderModel model) throws Exception;
	//删除维修单位
	public void delete(RepairProviderModel model) throws Exception;
	//得到指定的维修单位
	public RepairProviderModel get(int providerNo) throws Exception;
	//得到所有的维修单位列表
	public List<RepairProviderModel> getListByAll() throws Exception;
	//用分页得到所有的维修单位列表
	public List<RepairProviderModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得所有维修单位的页数
	public int getPageCountByAll(int rows) throws Exception;
	//得到所有的维修单位个数
	public int getCountByAll() throws Exception;
}
