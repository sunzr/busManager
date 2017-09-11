package com.neusoft.busManager.admin.service;

import java.util.List;

import com.neusoft.busManager.admin.model.UserModel;


public interface IUserService {
	//增加用户
	public void add(UserModel um) throws Exception;
	//修改用户
	public void modify(UserModel um) throws Exception;
	//删除用户
	public void delete(UserModel um) throws Exception;
	//
	public UserModel get(String userid) throws Exception;
	//取得所有管理员列表
	public List<UserModel> getListByAll() throws Exception;
	
	//取得所有管理员列表,分页模式
	public List<UserModel> getListByAllWithPage(int rows,int page) throws Exception;
	//取得所有操作员个数
	public int getCountByAll() throws Exception;
	//取得所有操作员页数
	public int getPageCountByAll(int rows) throws Exception;
	//操作员验证方法，用于登录验证
	public boolean validate(String userid,String password) throws Exception;
	//检查指定的操作员是否允许登录系统
	public boolean checkCanLogin(String userid) throws Exception;
	//检查指定的ID是否存在，用于增加新用户时检查
	public boolean checkUserExist(String userid) throws Exception;

	//修改操作员密码
	public void changePassword(UserModel um) throws Exception;
	public boolean checkCanDelete(String userid);
	public List<UserModel> selectWithoutFunction(String funno);
	
	//为指定的员工授予一个功能
	public void grantFunction(String userid,int functionNo) throws Exception;
	//为指定的员工授予多个功能
	public void grantFunctions(String userid,int[] functionNos) throws Exception;
}
