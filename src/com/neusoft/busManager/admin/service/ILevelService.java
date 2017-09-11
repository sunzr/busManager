package com.neusoft.busManager.admin.service;

import java.util.List;

import com.neusoft.busManager.admin.model.LevelManager;

//系统模块业务接口

public interface ILevelService {
	//取得指定的模块
	public LevelManager get(int moduleNo) throws Exception;
	//取得所有的模块列表
	public List<LevelManager> getListByAll() throws Exception;
	public int getCountByAll();
	public int getPageCountByAll(int rows);
	public List<LevelManager> getListByAllWithPage(int rows, int page);
	public void add(LevelManager um);
	public void delete(LevelManager um);
	
	public LevelManager selectByName(String lname);
}
