package com.neusoft.busManager.repairinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.repairinfo.mapper.IRepairTypeMapper;
import com.neusoft.busManager.repairinfo.model.RepairTypeModel;
import com.neusoft.busManager.repairinfo.service.IRepairTypeService;

@Service("RepairTypeService")
@Transactional
public class RepairTypeImpl implements IRepairTypeService {
	@Autowired
	private IRepairTypeMapper irtm;
	
	public void setIrtm(IRepairTypeMapper irtm) {
		this.irtm = irtm;
	}

	@Override
	public void add(RepairTypeModel repairTypeModel) throws Exception {
		// TODO Auto-generated method stub
		irtm.insert(repairTypeModel);
	}

	@Override
	public void modify(RepairTypeModel repairTypeModel) throws Exception {
		// TODO Auto-generated method stub
		irtm.update(repairTypeModel);
	}

	@Override
	public void delete(RepairTypeModel repairTypeModel) throws Exception {
		// TODO Auto-generated method stub
		irtm.delete(repairTypeModel);;
	}

	@Override
	public RepairTypeModel get(int typeNo) throws Exception {
		// TODO Auto-generated method stub
		return irtm.select(typeNo);
	}

	@Override
	public List<RepairTypeModel> getListByAll() throws Exception {
		// TODO Auto-generated method stub
		return irtm.selectListByAll();
	}
	
	@Override
	public List<RepairTypeModel> getListByAllWithPage(int rows,int page) throws Exception {
		// TODO Auto-generated method stub
		RowBounds rb=new RowBounds(rows*(page-1),rows); 
		return irtm.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {
		// TODO Auto-generated method stub
		return irtm.selectCountByAll();
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		// TODO Auto-generated method stub
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


}
