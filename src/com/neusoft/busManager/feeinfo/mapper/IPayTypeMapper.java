package com.neusoft.busManager.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.feeinfo.model.PayTypeModel;

public interface IPayTypeMapper {
	// 增加缴费类型
	public void insert(PayTypeModel ptm) throws Exception;

	// 修改缴费类型
	public void update(PayTypeModel ptm) throws Exception;

	// 修改缴费类型密码
	public void updateForPasswowrd(PayTypeModel ptm) throws Exception;

	// 删除缴费类型
	public void delete(PayTypeModel ptm) throws Exception;

	// 取得指定的缴费类型对象
	public PayTypeModel select(int PayTypeno) throws Exception;

	// 取得所有缴费类型列表，无分页
	public List<PayTypeModel> selectListByAll() throws Exception;

	// 取得所有缴费类型列表，有分页
	public List<PayTypeModel> selectListByAllWithPage(RowBounds rb) throws Exception;

	// 取得缴费类型个数
	public int selectCountByAll() throws Exception;

	// 校验缴费类型名称是否存在
	public int checkNameExist(String name) throws Exception;

}
