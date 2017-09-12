package com.neusoft.busManager.query.service.impl;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.query.mapper.IIllegalTypeMapper;
import com.neusoft.busManager.query.model.IllegalTypeModel;
import com.neusoft.busManager.query.service.IIllegalTypeService;

@Service("IIllegalTypeService")
@Transactional
public class IllegalTypeServiceImpl implements IIllegalTypeService{

	@Autowired
	private IIllegalTypeMapper illegalTypeMapper;
	
	@Override
	public IllegalTypeModel get(int moduleNo) throws Exception {
		return illegalTypeMapper.select(moduleNo);
	}

	@Override
	public List<IllegalTypeModel> getListByAll() throws Exception {
		return illegalTypeMapper.selectListByAll();
	}

	@Override
	public int getCountByAll() {
		
		return illegalTypeMapper.selectCountByAll();
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
	public List<IllegalTypeModel> getListByAllWithPage(int rows, int page) {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return illegalTypeMapper.selectListByAllWithPage(rb);
	}

	@Override
	public void add(IllegalTypeModel um) {
		illegalTypeMapper.add(um);
	}

	@Override
	public void delete(IllegalTypeModel um) {
		illegalTypeMapper.delete(um);
	}

	@Override
	public IllegalTypeModel selectByName(String lname) {
		return illegalTypeMapper.selectByName(lname);
	}

	@Override
	public void modify(IllegalTypeModel model) {
		illegalTypeMapper.modify(model);
	}
	
}
