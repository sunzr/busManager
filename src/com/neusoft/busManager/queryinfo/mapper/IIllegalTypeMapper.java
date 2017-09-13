package com.neusoft.busManager.queryinfo.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.queryinfo.model.IllegalTypeModel;


public interface IIllegalTypeMapper {
	//取得指定的功能模块对象
	public IllegalTypeModel select(int moduleNo) throws Exception;
	//取得所有模块列表
	public List<IllegalTypeModel> selectListByAll() throws Exception;
	public int selectCountByAll();
	public List<IllegalTypeModel> selectListByAllWithPage(RowBounds rb);
	public void add(IllegalTypeModel um);
	public void delete(IllegalTypeModel um);
	public IllegalTypeModel selectByName(String lname);
	public void modify(IllegalTypeModel model);
}
