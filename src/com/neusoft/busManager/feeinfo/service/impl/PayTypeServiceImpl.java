package com.neusoft.busManager.feeinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.feeinfo.mapper.IPayTypeMapper;
import com.neusoft.busManager.feeinfo.model.PayTypeModel;
import com.neusoft.busManager.feeinfo.service.IPayTypeService;

@Service("PayTypeService")
@Transactional
public class PayTypeServiceImpl implements IPayTypeService {

	@Autowired
	private IPayTypeMapper PayTypemapper;

	@Override
	public void add(PayTypeModel ptm) throws Exception {
		PayTypemapper.insert(ptm);
	}

	@Override
	public void modify(PayTypeModel ptm) throws Exception {
		PayTypemapper.update(ptm);
	}

	@Override
	public void delete(PayTypeModel ptm) throws Exception {
		PayTypemapper.delete(ptm);
	}

	@Override
	public PayTypeModel get(int typeno) throws Exception {
		return PayTypemapper.select(typeno);
	}

	@Override
	public List<PayTypeModel> getListByAll() throws Exception {
		return PayTypemapper.selectListByAll();
	}

	@Override
	public List<PayTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb = new RowBounds(rows * (page - 1), rows);
		return PayTypemapper.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {

		return PayTypemapper.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount = 0;
		int count = this.getCountByAll();
		if (count % rows == 0) {
			pageCount = count / rows;
		} else {
			pageCount = count / rows + 1;
		}
		return pageCount;
	}

	@Override
	public boolean checkPayTypeExist(String PayTypeid) throws Exception {
		return false;
	}

	@Override
	public boolean checkNameExist(String name) throws Exception {
		if(PayTypemapper.checkNameExist(name)>0){
			return true; 
		}else{
			return false; 
		}
	}

	@Override
	public boolean checkCanDelete(int typeno) throws Exception {
		//暂无关联的外键，可删除
		return true;
	}
}
