package com.neusoft.busManager.feeinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.feeinfo.mapper.ILaborCostTypeMapper;
import com.neusoft.busManager.feeinfo.model.LaborCostTypeModel;
import com.neusoft.busManager.feeinfo.service.ILaborCostTypeService;

@Service("LaborCostTypeService")
@Transactional
public class LaborCostTypeServiceImpl implements ILaborCostTypeService {

	@Autowired
	private ILaborCostTypeMapper LaborCostTypemapper;

	@Override
	public void add(LaborCostTypeModel lctm) throws Exception {
		LaborCostTypemapper.insert(lctm);
	}

	@Override
	public void modify(LaborCostTypeModel lctm) throws Exception {
		LaborCostTypemapper.update(lctm);
	}

	@Override
	public void delete(LaborCostTypeModel lctm) throws Exception {
		LaborCostTypemapper.delete(lctm);
	}

	@Override
	public LaborCostTypeModel get(int typeno) throws Exception {
		return LaborCostTypemapper.select(typeno);
	}

	@Override
	public List<LaborCostTypeModel> getListByAll() throws Exception {
		return LaborCostTypemapper.selectListByAll();
	}

	@Override
	public List<LaborCostTypeModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb = new RowBounds(rows * (page - 1), rows);
		return LaborCostTypemapper.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {

		return LaborCostTypemapper.selectCountByAll();
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
	public boolean checkNameExist(String name) throws Exception {
		if (LaborCostTypemapper.checkNameExist(name) > 0) {
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

}
