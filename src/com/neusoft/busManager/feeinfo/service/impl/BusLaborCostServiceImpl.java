package com.neusoft.busManager.feeinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.feeinfo.mapper.IBusLaborCostMapper;
import com.neusoft.busManager.feeinfo.model.BusLaborCostModel;
import com.neusoft.busManager.feeinfo.model.BusPayFeeModel;
import com.neusoft.busManager.feeinfo.service.IBusLaborCostService;

@Service("BusLaborCostService")
@Transactional
public class BusLaborCostServiceImpl implements IBusLaborCostService {

	@Autowired
	private IBusLaborCostMapper busLaborCostMapper;

	@Override
	public void add(BusLaborCostModel blcm) throws Exception {
		busLaborCostMapper.insert(blcm);
	}

	@Override
	public void modify(BusLaborCostModel blcm) throws Exception {
		busLaborCostMapper.update(blcm);
	}

	@Override
	public void delete(BusLaborCostModel blcm) throws Exception {
		busLaborCostMapper.delete(blcm);
	}

	@Override
	public BusLaborCostModel get(int costno) throws Exception {
		return busLaborCostMapper.select(costno);
	}

	@Override
	public List<BusLaborCostModel> getListByAll() throws Exception {
		return busLaborCostMapper.selectListByAll();
	}

	@Override
	public List<BusLaborCostModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb = new RowBounds(rows * (page - 1), rows);
		return busLaborCostMapper.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {

		return busLaborCostMapper.selectCountByAll();
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
	public boolean checkBusLaborCostExist(String BusLaborCostid) throws Exception {
		return false;
	}

	@Override
	public boolean checkNameExist(String name) throws Exception {
		if (busLaborCostMapper.checkNameExist(name) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkCanDelete(int costno) throws Exception {
		// 暂无关联的外键，可删除
		return true;
	}

	@Override
	public int getCountByCondition(int typeno, int busid, int driverid) throws Exception {
		return busLaborCostMapper.selectCountByCondition(typeno, busid, driverid);
	}

	@Override
	public List<BusLaborCostModel> getListByConditionWithPage(int typeno, int busid, int driverid, int rows, int page)
			throws Exception {
		RowBounds rb = new RowBounds(rows * (page - 1), rows);
		return busLaborCostMapper.selectListByCondition(typeno, busid, driverid, rb);
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
