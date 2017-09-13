package com.neusoft.busManager.baseinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.baseinfo.mapper.IBusMapper;
import com.neusoft.busManager.baseinfo.model.BusFactoryModel;
import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.baseinfo.service.IBusService;
import com.neusoft.busManager.queryinfo.mapper.IBusDayInfoMapper;

//车辆信息的业务实现类
@Service("BusService")
@Transactional
public class BusServiceImpl implements IBusService {
   private IBusMapper ibm=null;
   private IBusDayInfoMapper ibdm=null;
    @Autowired
	public void setIbm(IBusMapper ibm) {
		this.ibm = ibm;
	}
    
    @Autowired
	public void setIbdm(IBusDayInfoMapper ibdm) {
		this.ibdm = ibdm;
	}

	@Override
	public void add(BusModel bm) throws Exception {
		ibm.insert(bm);
	}

	@Override
	public void modify(BusModel bm) throws Exception {
		ibm.update(bm);
	}

	@Override
	public void delete(BusModel bm) throws Exception {
		ibm.delete(bm);
	}

	@Override
	public BusModel get(String busid) throws Exception {
		return ibm.select(busid);
	}
   

	@Override
	public BusModel selectWithBusDayInfo(String busid) throws Exception {
		return ibm.selectWithBusDayInfo(busid);
	}
	
	
	@Override
	public List<BusModel> getListByAll() throws Exception {
		return ibm.selectListByAll();
	}

	@Override
	public List<BusModel> getListByBusType(int typeno) throws Exception {
		return ibm.selectListByBusType(typeno);
	}

	@Override
	public List<BusModel> getListByBusFactory(int factoryno) throws Exception {
		return ibm.selectListByBusFactory(factoryno);
	}

	@Override
	public List<BusModel> getListByCondition(int typeno, int factoryno) throws Exception {
		return ibm.selectListByCondition(typeno, factoryno);
	}

	@Override
	public List<BusModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return ibm.selectListByAllWithPage(rb);
	}

	@Override
	public List<BusModel> getListByConditionWithPage(int typeno, int factoryno, int rows, int page) throws Exception {
		   RowBounds rb=new RowBounds(rows*(page-1),rows);
		   return ibm.selectListByConditionWithPage(typeno, factoryno, rb);
	}

	@Override
	public int getCountByAll() throws Exception {
		return ibm.selectCountByAll();
	}

	@Override
	public int getCountByCondition(int typeno, int factoryno) throws Exception {
		return ibm.selectCountByCondition(typeno, factoryno);
	}

	@Override
	public int getPageCountByCondition(int typeno, int factoryno, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(typeno, factoryno);
		if(count%rows==0){
			pageCount=count/rows;
		}
		else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}
    
	//检查指定车辆是否可以被删除
	@Override
	public boolean checkCanDelete(String busid) throws Exception {
		 boolean result=true;
		 //如果此车辆的日运行信息个数大于0，此车辆不能被删除
		 if(ibdm.selectCountByCondition(busid,null)>0){
			 result=false;
		 }
		 return result;
	}
     
}
