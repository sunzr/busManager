package com.neusoft.busManager.queryinfo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.queryinfo.mapper.IBusOilInfoMapper;
import com.neusoft.busManager.queryinfo.model.BusOilInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusOilInfoService;


@Transactional
@Service("busOilInfoService")
public class BusOilInfoServiceImpl implements IBusOilInfoService{

	@Autowired
	private IBusOilInfoMapper busoilInfoMapper;
	
	@Override
	public void insert(BusOilInfoModel boi) throws Exception {
		busoilInfoMapper.insert(boi);
	}

	@Override
	public void update(BusOilInfoModel boi) throws Exception {
		busoilInfoMapper.update(boi);
	}

	@Override
	public void delete(BusOilInfoModel boi) throws Exception {
		busoilInfoMapper.delete(boi);
	}

	@Override
	public BusOilInfoModel select(int infono) throws Exception {
		return busoilInfoMapper.select(infono);
	}

	@Override
	public List<BusOilInfoModel> selectListByAll() throws Exception {
		return busoilInfoMapper.selectListByAll();
	}

	@Override
	public List<BusOilInfoModel> selectListByBus(int busid) throws Exception {
		return busoilInfoMapper.selectListByBus(busid);
	}

	@Override
	public List<BusOilInfoModel> selectListByDriver(int driverid) throws Exception {
		return busoilInfoMapper.selectListByDriver(driverid);
	}

	@Override
	public List<BusOilInfoModel> selectListByConditionWithPage(int busid, int driverid) throws Exception {
		return busoilInfoMapper.selectListByConditionWithPage(busid, driverid);
	}

	@Override
	public List<BusOilInfoModel> selectListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return busoilInfoMapper.selectListByAllWithPage(rb);
	}

	@Override
	public List<BusOilInfoModel> selectListByConditionWithPage(int busid, int driverid,  int rows,
			int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return busoilInfoMapper.selectListByConditionWithPage(busid, driverid, rb);
	}

	@Override
	public int selectCountByAll() throws Exception {
		return busoilInfoMapper.selectCountByAll();
	}

	@Override
	public int selectCountByCondition(int busid, int driverid) throws Exception {
		return busoilInfoMapper.selectCountByCondition(busid, driverid);
	}

	@Override
	public int selectPageCountByCondition(int busNo, int busDriverNo, int rows) throws Exception {
		int pageCount=0;
		int count=this.selectCountByCondition(busNo, busDriverNo);
		if(count%rows==0){
			pageCount=count/rows;
		}
		else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
	
}
