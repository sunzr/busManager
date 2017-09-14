package com.neusoft.busManager.repairinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.repairinfo.mapper.IBusRepairInfoDetailMapper;
import com.neusoft.busManager.repairinfo.mapper.IRepairProviderMapper;
import com.neusoft.busManager.repairinfo.model.BusRepairInfoDetailModel;
import com.neusoft.busManager.repairinfo.model.RepairProviderModel;
import com.neusoft.busManager.repairinfo.service.IBusRepairInfoDetailService;
import com.neusoft.busManager.repairinfo.service.IRepairProviderService;

@Service("BusRepairInfoDetailService")
@Transactional
public class BusRepairInfoDetailImpl implements IBusRepairInfoDetailService {
	@Autowired
	private IBusRepairInfoDetailMapper ibridm;
	
	@Override
	public void add(BusRepairInfoDetailModel model) throws Exception {
		// TODO Auto-generated method stub
		ibridm.insert(model);
	}

	@Override
	public void modify(BusRepairInfoDetailModel model) throws Exception {
		// TODO Auto-generated method stub
		ibridm.update(model);
	}

	@Override
	public void delete(BusRepairInfoDetailModel model) throws Exception {
		// TODO Auto-generated method stub
		ibridm.delete(model);
	}

	@Override
	public BusRepairInfoDetailModel get(int DetailNo) throws Exception {
		// TODO Auto-generated method stub
		return ibridm.select(DetailNo);
	}

	@Override
	public List<BusRepairInfoDetailModel> getListByAll() throws Exception {
		// TODO Auto-generated method stub
		return ibridm.selectListByAll();
	}

	@Override
	public List<BusRepairInfoDetailModel> getListByAllWithPage(int rows, int page) throws Exception {
		// TODO Auto-generated method stub
		RowBounds rb=new  RowBounds(rows*(page-1),rows);
		return ibridm.selectListByAllWithPage(rb);
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
		return ibridm.selectCountByAll();
	}

}
