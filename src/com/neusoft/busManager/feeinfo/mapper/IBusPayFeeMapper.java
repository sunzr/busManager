package com.neusoft.busManager.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.feeinfo.model.BusPayFeeModel;

public interface IBusPayFeeMapper {
	// 增加车辆缴费
	public void insert(BusPayFeeModel bpfm) throws Exception;

	// 修改车辆缴费
	public void update(BusPayFeeModel bpfm) throws Exception;

	// 删除车辆缴费
	public void delete(BusPayFeeModel bpfm) throws Exception;

	// 取得指定的车辆缴费对象
	public BusPayFeeModel select(int payno) throws Exception;

	// 取得所有车辆缴费列表，无分页
	public List<BusPayFeeModel> selectListByAll() throws Exception;

	// 取得所有车辆缴费列表，有分页
	public List<BusPayFeeModel> selectListByAllWithPage(RowBounds rb) throws Exception;

	// 取得车辆缴费个数
	public int selectCountByAll() throws Exception;

	// 校验车辆缴费名称是否存在
	public int checkNameExist(String name) throws Exception;

	// 加检索条件搜索
	public List<BusPayFeeModel> selectListByCondition(@Param("typeno") int typeno, @Param("busid") int busid,
			@Param("driverid") int driverid, RowBounds rb) throws Exception;

	public int selectCountByCondition(@Param("typeno")int typeno,@Param("busid") int busid,@Param("driverid") int driverid) throws Exception;

}
