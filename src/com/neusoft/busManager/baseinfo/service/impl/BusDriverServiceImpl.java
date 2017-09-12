package com.neusoft.busManager.baseinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.baseinfo.mapper.IBusDriverMapper;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusFactoryModel;
import com.neusoft.busManager.baseinfo.service.IBusDriverService;

//司机信息的业务实现类
@Service("BusDriverService")
@Transactional
public class BusDriverServiceImpl implements IBusDriverService{
   private IBusDriverMapper  ibdm=null;
    @Autowired
	public void setIbdm(IBusDriverMapper ibdm) {
		this.ibdm = ibdm;
	}
	@Override
	public void add(BusDriverModel bdm) throws Exception {
		if(bdm.getPhotoFileName()!=null){
			ibdm.insertWithPhoto(bdm);
		}
		else{
		ibdm.insert(bdm);
		}
	}
	@Override
	public void modify(BusDriverModel bdm) throws Exception {
		ibdm.update(bdm);
	}
	
	@Override
	public void modifyWithPhoto(BusDriverModel bdm) throws Exception {
		ibdm.updateWithPhoto(bdm);
		
	}
	@Override
	public void modifyForDeletePhoto(BusDriverModel bdm) throws Exception {
		ibdm.updateForDeletePhoto(bdm);
	}
	
	@Override
	public void delete(BusDriverModel bdm) throws Exception {
		ibdm.delete(bdm);
	}
	@Override
	public BusDriverModel get(String driverid) throws Exception {
		return ibdm.select(driverid);
	}
	@Override
	public List<BusDriverModel> getListByAll() throws Exception {
		return ibdm.selectListByAll();
	}
	@Override
	public List<BusDriverModel> getListByAllWithPage(int rows, int page) throws Exception {
	          RowBounds rb=new RowBounds(rows*(page-1),rows);
	          return ibdm.selectListByAllWithPage(rb);
	}
	@Override
	public int getCountByAll() throws Exception {
		return ibdm.selectCountByAll();
	}
	@Override
	public int getPageCountByAll(int rows) throws Exception {
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
	//检查司机身份证号是否存在
	@Override
	public boolean checkDcardExist(String dcard) throws Exception {
		boolean result=false;
		List<BusDriverModel> list=this.getListByAll();
		for(BusDriverModel bd : list){
			if(bd!=null && bd.getDcard()!=null && bd.getDcard().equals(dcard)){
				result=true;
				break;
			}
		}
		return result;
	}
	
    
   
}
