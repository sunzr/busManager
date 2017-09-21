package com.neusoft.busManager.feeinfo.service;

import java.util.List;

import com.neusoft.busManager.feeinfo.model.LaborCostTypeModel;

public interface ILaborCostTypeService {
	// 增加人工费类型
	public void add(LaborCostTypeModel lctm) throws Exception;

	// 修改人工费类型
	public void modify(LaborCostTypeModel lctm) throws Exception;

	// 删除人工费类型
	public void delete(LaborCostTypeModel lctm) throws Exception;

	//
	public LaborCostTypeModel get(int typeno) throws Exception;

	// 取得所有人工费类型列表
	public List<LaborCostTypeModel> getListByAll() throws Exception;

	// 取得所有人工费类型列表,分页模式
	public List<LaborCostTypeModel> getListByAllWithPage(int rows, int page) throws Exception;

	// 取得所有人工费类型个数
	public int getCountByAll() throws Exception;

	// 取得所有人工费类型页数
	public int getPageCountByAll(int rows) throws Exception;

	// 检查指定的人工费类型名称是否存在，用于增加新人工费类型时检查
	public boolean checkNameExist(String name) throws Exception;

	// 检查指定的人工费类型是否可以删除，通过判断是否有外键关联
	public boolean checkCanDelete(int typeno) throws Exception;

}
