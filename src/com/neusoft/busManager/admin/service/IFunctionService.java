package com.neusoft.busManager.admin.service;

import java.util.List;

import com.neusoft.busManager.admin.model.FunctionModel;

//系统功能业务接口

public interface IFunctionService {
	//取得指定的功能
	public FunctionModel get(int functionNo) throws Exception;
	//取得所有功能列表
	public List<FunctionModel> getListByAll() throws Exception;
	//取得指定用户的功能列表
	public List<FunctionModel> getListByUser(String userid) throws Exception;
	//给指定的用户授权
	public void grant(String functionNo,int[] userids) throws Exception;

	

}
