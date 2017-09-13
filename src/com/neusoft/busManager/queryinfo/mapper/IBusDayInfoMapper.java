package com.neusoft.busManager.queryinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;

//车辆日运行表的Mapper接口
public interface IBusDayInfoMapper {
    //增加车辆日运行信息
	public void insert(BusDayInfoModel bim)throws Exception;
	//修改车辆日运行信息
	public void update(BusDayInfoModel bim)throws Exception;
	//删除车辆日运行信息
	public void delete(BusDayInfoModel bim)throws Exception;
	//按序号取得指定的车辆日运行信息
	public BusDayInfoModel select(int infono)throws Exception;
	//取得所有的车辆日运行信息
	public List<BusDayInfoModel> selectListByAll()throws Exception;
	
	//按车辆取得车辆日运行信息
	public List<BusDayInfoModel> selectListByBus(String busid)throws Exception;
	
	//按司机对象取得车辆日运行信息
	public List<BusDayInfoModel> selectListByBusDriver(String driverid)throws Exception;
	
	//按检索条件取得所有车辆日运行信息
	   public List<BusDayInfoModel> selectListByCondition(@Param("busid")String busid,@Param("driverid")String driverid) throws Exception;
	//分页方式取得所有车辆日运行信息
	public List<BusDayInfoModel> selectListByAllWithPage(RowBounds rb)throws Exception;
	
	//按检索条件取得所有车辆日运行信息，分页方式
	public List<BusDayInfoModel> selectListByConditionWithPage(@Param("busid")String busid,@Param("driverid")String driverid,RowBounds rb) throws Exception;		
	//取得车辆日运行信息的个数
	public int selectCountByAll()throws Exception;
	
	//按检索条件取得车辆日运行信息的个数
	   public int selectCountByCondition(@Param("busid")String busid,@Param("driverid")String driverid) throws Exception;
	
}
