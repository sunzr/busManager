package com.neusoft.busManager.queryinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.queryinfo.model.BusIllegalFeeModel;

//车辆违章的mapper接口
public interface IBusIllegalFeeMapper {
    //增加车辆违章信息
	public void insert(BusIllegalFeeModel bif)throws Exception;
	//增加车辆违章信息
		public void update(BusIllegalFeeModel bif)throws Exception;
	//增加车辆违章信息
	public void delete(BusIllegalFeeModel bif)throws Exception;
	
	//取得指定的车辆违章信息
	public BusIllegalFeeModel select(int feeno)throws Exception;
	
	//取得所有的车辆违章信息
	public List<BusIllegalFeeModel> selectListByAll()throws Exception;
	
	//按车辆编号取得车辆违章信息
		public List<BusIllegalFeeModel> selectListByBus(int busid)throws Exception;
	//按司机编号取得车辆违章信息
			public List<BusIllegalFeeModel> selectListByBusDriver(int driverid)throws Exception;
	//按违章类型取得车辆违章信息
	public List<BusIllegalFeeModel> selectListByIllgealType(int typeno)throws Exception;
	//按条件取得所有车辆违章信息
	public List<BusIllegalFeeModel> selectListByCondition(@Param("busid")int busid,@Param("driverid") int driverid,@Param("typeno") int typeno) throws Exception;
	//分页方式取得所有车辆违章信息
	public List<BusIllegalFeeModel> selectListByAllWithPage(RowBounds rb)throws Exception;
	
	//按检索条件取得所有车辆违章信息，分页方式
	public List<BusIllegalFeeModel> selectListByConditionWithPage(@Param("busid")int busid,@Param("driverid") int driverid,@Param("typeno") int typeno,RowBounds rb) throws Exception;
	//取得车辆违章信息的个数
	public int selectCountByAll() throws Exception;
	//按检索条件取得车辆违章信息的个数
	 public int selectCountByCondition(@Param("busid")int busid,@Param("driverid") int driverid,@Param("typeno") int typeno) throws Exception;
}
