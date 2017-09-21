package com.neusoft.busManager.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.feeinfo.model.BusLaborCostModel;

public interface IBusLaborCostMapper {
	// 增加车辆人工费
	public void insert(BusLaborCostModel blcm) throws Exception;

	// 修改车辆人工费
	public void update(BusLaborCostModel blcm) throws Exception;

	// 删除车辆人工费
	public void delete(BusLaborCostModel blcm) throws Exception;

	// 取得指定的车辆人工费对象
	public BusLaborCostModel select(int costno) throws Exception;

	// 取得所有车辆人工费列表，无分页
	public List<BusLaborCostModel> selectListByAll() throws Exception;

	// 取得所有车辆人工费列表，有分页
	public List<BusLaborCostModel> selectListByAllWithPage(RowBounds rb) throws Exception;

	// 取得车辆人工费个数
	public int selectCountByAll() throws Exception;

	// 校验车辆人工费名称是否存在
	public int checkNameExist(String name) throws Exception;

	// 加检索条件搜索
	public List<BusLaborCostModel> selectListByCondition(@Param("typeno") int typeno, @Param("busid") int busid,
			@Param("driverid") int driverid, RowBounds rb) throws Exception;

	public int selectCountByCondition(@Param("typeno") int typeno, @Param("busid") int busid,
			@Param("driverid") int driverid) throws Exception;

}
