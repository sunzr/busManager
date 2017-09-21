package com.neusoft.busManager.feeinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.feeinfo.mapper.IBusPayFeeMapper;
import com.neusoft.busManager.feeinfo.model.BusPayFeeModel;
import com.neusoft.busManager.feeinfo.service.IBusPayFeeService;

@Service("BusPayFeeService")
@Transactional
public class BusPayFeeServiceImpl implements IBusPayFeeService {

	@Autowired
	private IBusPayFeeMapper busPayFeemapper;

	@Override
	public void add(BusPayFeeModel ptm) throws Exception {
		busPayFeemapper.insert(ptm);
	}

	@Override
	public void modify(BusPayFeeModel ptm) throws Exception {
		busPayFeemapper.update(ptm);
	}

	@Override
	public void delete(BusPayFeeModel ptm) throws Exception {
		busPayFeemapper.delete(ptm);
	}

	@Override
	public BusPayFeeModel get(int typeno) throws Exception {
		return busPayFeemapper.select(typeno);
	}

	@Override
	public List<BusPayFeeModel> getListByAll() throws Exception {
		return busPayFeemapper.selectListByAll();
	}

	@Override
	public List<BusPayFeeModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb = new RowBounds(rows * (page - 1), rows);
		return busPayFeemapper.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {

		return busPayFeemapper.selectCountByAll();
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
	public boolean checkBusPayFeeExist(String BusPayFeeid) throws Exception {
		return false;
	}

	@Override
	public boolean checkNameExist(String name) throws Exception {
		if (busPayFeemapper.checkNameExist(name) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkCanDelete(int typeno) throws Exception {
		// 暂无关联的外键，可删除
		return true;
	}

	@Override
	public int getCountByCondition(int typeno, int busid, int driverid) throws Exception {
		return busPayFeemapper.selectCountByCondition(typeno, busid, driverid);
	}

	@Override
	public List<BusPayFeeModel> getListByConditionWithPage(int typeno, int busid, int driverid, int rows, int page)
			throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return busPayFeemapper.selectListByCondition(typeno, busid, driverid,rb);
	}

	@Override
	public int getPageCountByCondition(int typeno, int busid, int driverid, int rows) throws Exception {
		int pageCount = 0;
		int count = this.getCountByCondition(typeno, busid, rows);
		if (count % rows == 0) {
			pageCount = count / rows;
		} else {
			pageCount = count / rows + 1;
		}
		return pageCount;

	}

}
