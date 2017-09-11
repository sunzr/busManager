package com.neusoft.busManager.feeinfo.service;

import java.util.List;

import com.neusoft.busManager.feeinfo.model.PayTypeModel;

public interface IPayTypeService {
	// 增加用户
	public void add(PayTypeModel ptm) throws Exception;

	// 修改用户
	public void modify(PayTypeModel ptm) throws Exception;

	// 删除用户
	public void delete(PayTypeModel ptm) throws Exception;

	//
	public PayTypeModel get(int typeno) throws Exception;

	// 取得所有管理员列表
	public List<PayTypeModel> getListByAll() throws Exception;

	// 取得所有管理员列表,分页模式
	public List<PayTypeModel> getListByAllWithPage(int rows, int page) throws Exception;

	// 取得所有操作员个数
	public int getCountByAll() throws Exception;

	// 取得所有操作员页数
	public int getPageCountByAll(int rows) throws Exception;

	// 检查指定的ID是否存在，用于增加新用户时检查
	public boolean checkPayTypeExist(String PayTypeid) throws Exception;

}
