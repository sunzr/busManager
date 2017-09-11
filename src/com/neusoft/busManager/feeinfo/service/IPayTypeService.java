package com.neusoft.busManager.feeinfo.service;

import java.util.List;

import com.neusoft.busManager.feeinfo.model.PayTypeModel;

public interface IPayTypeService {
	// 增加缴费类型
	public void add(PayTypeModel ptm) throws Exception;

	// 修改缴费类型
	public void modify(PayTypeModel ptm) throws Exception;

	// 删除缴费类型
	public void delete(PayTypeModel ptm) throws Exception;

	//
	public PayTypeModel get(int typeno) throws Exception;

	// 取得所有缴费类型列表
	public List<PayTypeModel> getListByAll() throws Exception;

	// 取得所有缴费类型列表,分页模式
	public List<PayTypeModel> getListByAllWithPage(int rows, int page) throws Exception;

	// 取得所有缴费类型个数
	public int getCountByAll() throws Exception;

	// 取得所有缴费类型页数
	public int getPageCountByAll(int rows) throws Exception;

	// 检查指定的ID是否存在，用于增加新缴费类型时检查
	public boolean checkPayTypeExist(String PayTypeid) throws Exception;

	// 检查指定的缴费类型名称是否存在，用于增加新缴费类型时检查
	public boolean checkNameExist(String name) throws Exception;

	// 检查指定的缴费类型是否可以删除，通过判断是否有外键关联
	public boolean checkCanDelete(int typeno)throws Exception;

}
