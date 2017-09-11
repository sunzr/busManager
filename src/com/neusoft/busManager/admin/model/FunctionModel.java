package com.neusoft.busManager.admin.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("Function")
public class FunctionModel {
	
	private int funno;
	private String funname;
	private String funurl;
	
	private LevelManager level;
	//拥有此功能的用户列表
	private List<UserModel> users;
	
	
	public LevelManager getLevel() {
		return level;
	}
	public void setLevel(LevelManager level) {
		this.level = level;
	}
	public int getFunno() {
		return funno;
	}
	public void setFunno(int funno) {
		this.funno = funno;
	}
	public String getFunname() {
		return funname;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
	public String getFunurl() {
		return funurl;
	}
	public void setFunurl(String funurl) {
		this.funurl = funurl;
	}
	public List<UserModel> getUsers() {
		return users;
	}
	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	
	
	
}
