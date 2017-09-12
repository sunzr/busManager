package com.neusoft.busManager.query.service;

import java.util.List;
import com.neusoft.busManager.query.model.IllegalTypeModel;

public interface IIllegalTypeService {
	//取得指定的模块
	public IllegalTypeModel get(int moduleNo) throws Exception;
	//取得所有的模块列表
	public List<IllegalTypeModel> getListByAll() throws Exception;
	public int getCountByAll();
	public int getPageCountByAll(int rows);
	public List<IllegalTypeModel> getListByAllWithPage(int rows, int page);
	public void add(IllegalTypeModel um);
	public void delete(IllegalTypeModel um);
	
	public IllegalTypeModel selectByName(String lname);
	public void modify(IllegalTypeModel model);
}
