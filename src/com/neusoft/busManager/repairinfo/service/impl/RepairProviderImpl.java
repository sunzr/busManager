package com.neusoft.busManager.repairinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.repairinfo.mapper.IRepairProviderMapper;
import com.neusoft.busManager.repairinfo.model.RepairProviderModel;
import com.neusoft.busManager.repairinfo.service.IRepairProviderService;

@Service("RepairProviderService")
@Transactional
public class RepairProviderImpl implements IRepairProviderService {
	@Autowired
	private IRepairProviderMapper irpm;
	
	@Override
	public void add(RepairProviderModel model) throws Exception {
		// TODO Auto-generated method stub
		irpm.insert(model);
	}

	@Override
	public void modify(RepairProviderModel model) throws Exception {
		// TODO Auto-generated method stub
		irpm.update(model);
	}

	@Override
	public void delete(RepairProviderModel model) throws Exception {
		// TODO Auto-generated method stub
		irpm.delete(model);
	}

	@Override
	public RepairProviderModel get(int providerNo) throws Exception {
		// TODO Auto-generated method stub
		return irpm.select(providerNo);
	}

	@Override
	public List<RepairProviderModel> getListByAll() throws Exception {
		// TODO Auto-generated method stub
		return irpm.selectListByAll();
	}

	@Override
	public List<RepairProviderModel> getListByAllWithPage(int rows,int page) throws Exception {
		// TODO Auto-generated method stub
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return irpm.selectListByAllWithPage(rb);
	}

	@Override
	public int getPageCountByAll(int rows) throws Exception {
		// TODO Auto-generated method stub
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0){
			pageCount=count/rows;
		}else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
	@Override
	public int getCountByAll() throws Exception {
		// TODO Auto-generated method stub
		return irpm.selectCountByAll();
	}


}
