package com.neusoft.busManager.util;

//用户结果返回，用于取得登录用户信息
public class UserInfo<UserModel> {

	private String result=null;
	private String message=null;
	private UserModel user=null;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	
}
