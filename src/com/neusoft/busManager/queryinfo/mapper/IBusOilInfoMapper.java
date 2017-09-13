package com.neusoft.busManager.queryinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.queryinfo.model.BusOilInfoModel;



public interface IBusOilInfoMapper {
	public void insert(BusOilInfoModel boi) throws Exception;
	public void update(BusOilInfoModel boi) throws Exception;
	public void delete(BusOilInfoModel boi) throws Exception;
	public BusOilInfoModel select(int infono) throws Exception;
	public List<BusOilInfoModel> selectListByAll() throws Exception;

	public List<BusOilInfoModel> selectListByBus(int busid) throws Exception;
	public List<BusOilInfoModel> selectListByDriver(int driverid) throws Exception;
	public List<BusOilInfoModel> selectListByConditionWithPage(@Param("busid") int busid,@Param("driverid") int driverid) throws Exception;

	public List<BusOilInfoModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	public List<BusOilInfoModel> selectListByConditionWithPage(@Param("busid") int busid,@Param("driverid") int driverid,RowBounds rb) throws Exception;
	public int selectCountByAll() throws Exception;
	public int selectCountByCondition(@Param("busid") int busid,@Param("driverid") int driverid) throws Exception;

}	
