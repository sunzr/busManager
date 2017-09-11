package com.neusoft.busManager.admin.model;

import org.apache.ibatis.type.Alias;

@Alias("Level")
public class LevelManager {
	private int lid;
	private String lname;
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
