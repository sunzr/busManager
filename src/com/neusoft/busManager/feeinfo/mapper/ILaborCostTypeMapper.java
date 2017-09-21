package com.neusoft.busManager.feeinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.feeinfo.model.LaborCostTypeModel;

public interface ILaborCostTypeMapper {
	// 增加人工费类型
	public void insert(LaborCostTypeModel lctm) throws Exception;

	// 修改人工费类型
	public void update(LaborCostTypeModel lctm) throws Exception;

	// 修改人工费类型密码
	public void updateForPasswowrd(LaborCostTypeModel lctm) throws Exception;

	// 删除人工费类型
	public void delete(LaborCostTypeModel lctm) throws Exception;

	// 取得指定的人工费类型对象
	public LaborCostTypeModel select(int typeno) throws Exception;

	// 取得所有人工费类型列表，无分页
	public List<LaborCostTypeModel> selectListByAll() throws Exception;

	// 取得所有人工费类型列表，有分页
	public List<LaborCostTypeModel> selectListByAllWithPage(RowBounds rb) throws Exception;

	// 取得人工费类型个数
	public int selectCountByAll() throws Exception;

	// 校验人工费类型名称是否存在
	public int checkNameExist(String name) throws Exception;

}
