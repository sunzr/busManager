package com.neusoft.busManager.admin.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class UserModel {
	private String userid;
	private String password;
	private String name;
	
	private List<FunctionModel> functions;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FunctionModel> getFunctions() {
		return functions;
	}
	public void setFunctions(List<FunctionModel> functions) {
		this.functions = functions;
	}
	
	
}
