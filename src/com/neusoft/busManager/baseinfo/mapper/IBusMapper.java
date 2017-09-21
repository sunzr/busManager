package com.neusoft.busManager.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.baseinfo.model.BusModel;

//车辆信息的Mapper接口
public interface IBusMapper {
	// 增加车辆信息
	public void insert(BusModel bm) throws Exception;

	// 修改车辆信息
	public void update(BusModel bm) throws Exception;

	// 删除车辆信息
	public void delete(BusModel bm) throws Exception;

	// 取得指定的车辆信息
	public BusModel select(int busid) throws Exception;

	// 取得所有车辆信息
	public List<BusModel> selectListByAll() throws Exception;

	// 按车辆类型取得车辆信息
	public List<BusModel> selectListByBusType(int typeno) throws Exception;

	// 按车辆厂家编号取得车辆信息
	public List<BusModel> selectListByBusFactory(int factoryno) throws Exception;

	// 按条件取得所有车辆信息
	public List<BusModel> selectListByCondition(@Param("typeno") int typeno, @Param("factoryno") int factoryno)
			throws Exception;

	// 分页方式取得所有车辆信息
	public List<BusModel> selectListByAllWithPage(RowBounds rb) throws Exception;

	// 按检索条件取得所有车辆信息，分页方式
	public List<BusModel> selectListByConditionWithPage(@Param("typeno") int typeno, @Param("factoryno") int factoryno,
			RowBounds rb) throws Exception;

	// 取得车辆信息的个数
	public int selectCountByAll() throws Exception;

	// 按检索条件取得车辆信息的个数
	public int selectCountByCondition(@Param("typeno") int typeno, @Param("factoryno") int factoryno) throws Exception;
}
