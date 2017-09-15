package com.neusoft.busManager.queryinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.queryinfo.mapper.IBusIllegalFeeMapper;
import com.neusoft.busManager.queryinfo.model.BusIllegalFeeModel;
import com.neusoft.busManager.queryinfo.service.IBusIllegalFeeService;

//车辆违章信息的业务实现类
@Service("BusIllegalFeeService")
@Transactional
public class BusIllegalFeeServiceImpl implements IBusIllegalFeeService {
      private  IBusIllegalFeeMapper ibfm=null;
      @Autowired
	public void setIbfm(IBusIllegalFeeMapper ibfm) {
		this.ibfm = ibfm;
	}

	@Override
	public void add(BusIllegalFeeModel bif) throws Exception {
		ibfm.insert(bif);
	}

	@Override
	public void modify(BusIllegalFeeModel bif) throws Exception {
		ibfm.update(bif);
		
	}

	@Override
	public void delete(BusIllegalFeeModel bif) throws Exception {
		ibfm.delete(bif);
		
	}

	@Override
	public BusIllegalFeeModel get(int feeno) throws Exception {
		return ibfm.select(feeno);
	}

	@Override
	public List<BusIllegalFeeModel> getListByAll() throws Exception {
		return ibfm.selectListByAll();
	}

	@Override
	public List<BusIllegalFeeModel> getListByBus(int busid) throws Exception {
		return ibfm.selectListByBus(busid);
	}

	@Override
	public List<BusIllegalFeeModel> getListByBusDriver(int driverid) throws Exception {
		return ibfm.selectListByBusDriver(driverid);
	}

	@Override
	public List<BusIllegalFeeModel> getListByIllgealType(int typeno) throws Exception {
		return ibfm.selectListByIllgealType(typeno);
	}

	@Override
	public List<BusIllegalFeeModel> getListByCondition(int busid, int driverid, int typeno) throws Exception {
		return ibfm.selectListByCondition(busid, driverid, typeno);
	}

	@Override
	public List<BusIllegalFeeModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return ibfm.selectListByAllWithPage(rb);
	}

	@Override
	public List<BusIllegalFeeModel> getListByConditionWithPage(int busid, int driverid, int typeno, int rows, int page)
			throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return ibfm.selectListByConditionWithPage(busid, driverid, typeno, rb);
	}

	@Override
	public int getCountByAll() throws Exception {
		return ibfm.selectCountByAll();
	}

	@Override
	public int getCountByCondition(int busid, int driverid, int typeno) throws Exception {
		return ibfm.selectCountByCondition(busid, driverid, typeno);
	}

	@Override
	public int getPageCountByCondition(int busid, int driverid, int typeno, int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByCondition(busid, driverid, typeno);
		if(count%rows==0){
			pageCount=count/rows;
		}
		else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}

}
