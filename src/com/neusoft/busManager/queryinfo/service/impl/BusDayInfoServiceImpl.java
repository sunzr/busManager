package com.neusoft.busManager.queryinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.queryinfo.mapper.IBusDayInfoMapper;
import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusDayInfoService;

//车辆日运行信息的业务实现类
@Service("BusDayInfoService")
@Transactional
public class BusDayInfoServiceImpl implements IBusDayInfoService{
       private IBusDayInfoMapper  ibdm=null;
   @Autowired
	public void setIbdm(IBusDayInfoMapper ibdm) {
		this.ibdm = ibdm;
	}
			@Override
			public void add(BusDayInfoModel bim) throws Exception {
				ibdm.insert(bim);
			}
		@Override
		public void modify(BusDayInfoModel bim) throws Exception {
		ibdm.update(bim);
		}
	@Override
	public void delete(BusDayInfoModel bim) throws Exception {
		ibdm.delete(bim);
		
	}
		@Override
		public BusDayInfoModel get(int infono) throws Exception {
			return ibdm.select(infono);
		}
	@Override
	public List<BusDayInfoModel> getListByAll() throws Exception {
		return ibdm.selectListByAll();
	}
			@Override
			public List<BusDayInfoModel> getListByBus(int busid) throws Exception {
				return ibdm.selectListByBus(busid);
			}
	@Override
	public List<BusDayInfoModel> getListByBusDriver(int driverid) throws Exception {
		return ibdm.selectListByBusDriver(driverid);
	}
@Override
public List<BusDayInfoModel> gettListByCondition(int busid, int driverid) throws Exception {
	return ibdm.selectListByCondition(busid, driverid);
}
	@Override
	public List<BusDayInfoModel> getListByAllWithPage(int rows, int page) throws Exception {
		 RowBounds rb=new RowBounds(rows*(page-1),rows);
		return ibdm.selectListByAllWithPage(rb);
	}
@Override
public List<BusDayInfoModel> getListByConditionWithPage(int busid, int driverid, int rows, int page)
		throws Exception {
	RowBounds rb=new RowBounds(rows*(page-1),rows);
	return ibdm.selectListByConditionWithPage(busid, driverid, rb);
}
		@Override
		public int getCountByAll() throws Exception {
			return ibdm.selectCountByAll();
		}
@Override
public int getCountByCondition(int busid,int driverid) throws Exception {
	return ibdm.selectCountByCondition(busid, driverid);
}
		@Override
		public int getPageCountByCondition(int busid, int driverid, int rows) throws Exception {
			int pageCount=0;
			int count=this.getCountByCondition(busid, driverid);
			if(count%rows==0){
				pageCount=count/rows;
			}
			else{
				pageCount=count/rows+1;
			}
			return pageCount;
		    }
}
   
      
