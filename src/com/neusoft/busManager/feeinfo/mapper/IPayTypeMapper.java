package com.neusoft.busManager.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.feeinfo.model.PayTypeModel;

public interface IPayTypeMapper {
	// 增加操作员
	public void insert(PayTypeModel ptm) throws Exception;

	// 修改操作员
	public void update(PayTypeModel ptm) throws Exception;

	// 修改操作员密码
	public void updateForPasswowrd(PayTypeModel ptm) throws Exception;

	// 删除操作员
	public void delete(PayTypeModel ptm) throws Exception;

	// 取得指定的操作员对象
	public PayTypeModel select(int PayTypeno) throws Exception;

	// 取得所有操作员列表，无分页
	public List<PayTypeModel> selectListByAll() throws Exception;

	// 取得所有操作员列表，有分页
	public List<PayTypeModel> selectListByAllWithPage(RowBounds rb) throws Exception;

	// 取得操作员个数
	public int selectCountByAll() throws Exception;

}
