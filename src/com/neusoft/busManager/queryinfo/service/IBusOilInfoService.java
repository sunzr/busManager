package com.neusoft.busManager.queryinfo.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.queryinfo.model.BusOilInfoModel;

public interface IBusOilInfoService {
	public void insert(BusOilInfoModel boi) throws Exception;
	public void update(BusOilInfoModel boi) throws Exception;
	public void delete(BusOilInfoModel boi) throws Exception;
	public BusOilInfoModel select(int infono) throws Exception;
	public List<BusOilInfoModel> selectListByAll() throws Exception;

	public List<BusOilInfoModel> selectListByBus(int busid) throws Exception;
	public List<BusOilInfoModel> selectListByDriver(int driverid) throws Exception;
	public List<BusOilInfoModel> selectListByConditionWithPage(int busid, int driverid) throws Exception;

	public List<BusOilInfoModel> selectListByAllWithPage(int rows, int page) throws Exception;
	public List<BusOilInfoModel> selectListByConditionWithPage( int busid, int driverid,  int rows,	int page) throws Exception;
	public int selectCountByAll() throws Exception;
	public int selectCountByCondition( int busid, int driverid) throws Exception;
	public int selectPageCountByCondition(int busNo, int busDriverNo, int rows)throws Exception ;
}
