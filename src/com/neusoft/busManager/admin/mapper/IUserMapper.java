package com.neusoft.busManager.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.admin.model.UserModel;

public interface IUserMapper {
	//增加操作员
	public void insert(UserModel um) throws Exception;
	//修改操作员
	public void update(UserModel um) throws Exception;
	//修改操作员密码
	public void updateForPasswowrd(UserModel um) throws Exception;
	//删除操作员
	public void delete(UserModel um) throws Exception;
	//取得指定的操作员对象
	public UserModel select(String userid) throws Exception;
	//取得所有操作员列表，无分页
	public List<UserModel> selectListByAll() throws Exception;
	//取得所有操作员列表，有分页
	public List<UserModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	//取得操作员个数
	public int selectCountByAll() throws Exception;

	public List<UserModel> selectWithoutFunction(String funno);
	
	//为指定员工授予指定的功能
	public void grantFunction(@Param("userid") String userid,@Param("functionNo") int functionNo) throws Exception;
	//为指定员工授予指定的功能
	public void grantFunctions(@Param("userid") String userid,@Param("functionNos") int[] functionNos) throws Exception;
	//取得指定功能的用户列表
	public List<UserModel> selectListByFunction(int functionNo) throws Exception;
	public void revoleFunctions(String userid);
}
