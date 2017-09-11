package com.neusoft.busManager.admin.mapper;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.admin.model.LevelManager;


public interface ILevelManagerMapper {
	//取得指定的功能模块对象
	public LevelManager select(int moduleNo) throws Exception;
	//取得所有模块列表
	public List<LevelManager> selectListByAll() throws Exception;
	public int selectCountByAll();
	public List<LevelManager> selectListByAllWithPage(RowBounds rb);
	public void add(LevelManager um);
	public void delete(LevelManager um);
	public LevelManager selectByName(String lname);
}
