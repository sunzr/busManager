package com.neusoft.busManager.admin.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.admin.mapper.IFunctionMapper;
import com.neusoft.busManager.admin.mapper.IUserMapper;
import com.neusoft.busManager.admin.model.FunctionModel;
import com.neusoft.busManager.admin.model.UserModel;
import com.neusoft.busManager.admin.service.IUserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserMapper usermapper;
	@Autowired
	private IFunctionMapper functionMapper;
	
	@Override
	public void add(UserModel um) throws Exception {
		usermapper.insert(um);
	}

	@Override
	public void modify(UserModel um) throws Exception {
		usermapper.update(um);
	}

	@Override
	public void delete(UserModel um) throws Exception {
		usermapper.delete(um);
	}

	@Override
	public UserModel get(String userid) throws Exception {
		return usermapper.select(userid);
	}

	@Override
	public List<UserModel> getListByAll() throws Exception {
		return usermapper.selectListByAll();
	}

	@Override
	public List<UserModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return usermapper.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return usermapper.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0){
			pageCount=count/rows;
		}
		else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}

	@Override
	public boolean validate(String userid, String password) throws Exception {
		boolean result=false;
		UserModel um=usermapper.select(userid);
		if(um!=null&&um.getPassword()!=null&&um.getPassword().equals(password)){
			result=true;
		}
		return result;
	}

	@Override
	public boolean checkCanLogin(String userid) throws Exception {
		return true;
	}

	@Override
	public boolean checkUserExist(String userid) throws Exception {
		return false;
	}

	@Override
	public void changePassword(UserModel um) throws Exception {
		usermapper.updateForPasswowrd(um);
	}

	@Override
	public boolean checkCanDelete(String userid) {
		return true;
	}

	@Override
	public List<UserModel> selectWithoutFunction(String funno) {
		
		return usermapper.selectWithoutFunction(funno);
	}

	@Override
	public void grantFunction(String userid, int functionNo) throws Exception {
		usermapper.grantFunction(userid, functionNo);
	}

	@Override
	public void grantFunctions(String userid, int[] functionNos) throws Exception {
		usermapper.grantFunctions(userid, functionNos);
	}

	//取得指定员工的功能列表
	@Override
	public List<FunctionModel> getFunctionsByUser(String userid) throws Exception {
		
		return functionMapper.selectListByUser(userid);
	}

	@Override
	public void revoleFunctions(String userid) {
		usermapper.revoleFunctions(userid);		
	}
}
