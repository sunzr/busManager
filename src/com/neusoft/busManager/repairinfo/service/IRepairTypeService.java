package com.neusoft.busManager.repairinfo.service;

import java.util.List;

import com.neusoft.busManager.repairinfo.model.RepairTypeModel;

public interface IRepairTypeService {
	//增加维修类型
	public void add (RepairTypeModel repairTypeModel) throws Exception;
	//修改维修类型
	public void modify (RepairTypeModel repairTypeModel) throws Exception;
	//删除维修类型
	public void delete (RepairTypeModel repairTypeModel) throws Exception;
	//取得指定的维修类型
	public RepairTypeModel get (int typeNo) throws Exception;
	//取得所有的维修类型
	public List<RepairTypeModel> getListByAll () throws Exception;
	//以分页方式取得所有的维修类型列表
	public List<RepairTypeModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得所有维修类型的个数
	public int getCountByAll () throws Exception;
	//取得所有的维修类型的页数
	public int getPageCountByAll (int rows) throws Exception;
}
