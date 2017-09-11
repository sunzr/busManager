package com.neusoft.busManager.admin.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.admin.mapper.ILevelManagerMapper;
import com.neusoft.busManager.admin.model.LevelManager;
import com.neusoft.busManager.admin.service.ILevelService;

@Service("LevelService")
@Transactional
public class LevelServiceImpl implements ILevelService {

	@Autowired	
	private ILevelManagerMapper levelmanager;
	
	@Override
	public LevelManager get(int moduleNo) throws Exception {
		
		return levelmanager.select(moduleNo);
	}

	@Override
	public List<LevelManager> getListByAll() throws Exception {
		return levelmanager.selectListByAll();
	}

	@Override
	public int getCountByAll() {
		return levelmanager.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) {
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
	public List<LevelManager> getListByAllWithPage(int rows, int page) {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return levelmanager.selectListByAllWithPage(rb);
	}

	@Override
	public void add(LevelManager um) {
		levelmanager.add(um);
	}

	@Override
	public void delete(LevelManager um) {
		levelmanager.delete(um);
	}

	@Override
	public LevelManager selectByName(String lname) {
		return levelmanager.selectByName(lname);
	}

}
