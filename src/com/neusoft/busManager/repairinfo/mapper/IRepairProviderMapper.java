package com.neusoft.busManager.repairinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.repairinfo.model.RepairProviderModel;

//方法名得和相应的 mapper.xml 中的 id 保持一致
public interface IRepairProviderMapper {
	//增加维修单位
	public void insert(RepairProviderModel model) throws Exception;
	//修改维修单位
	public void update(RepairProviderModel model) throws Exception;
	//删除维修单位
	public void delete(RepairProviderModel model) throws Exception;
	//查找指定的维修单位
	public RepairProviderModel select(int providerNo) throws Exception;
	//查找所有的维修单位列表
	public List<RepairProviderModel> selectListByAll() throws Exception;
	//以分页的形式查找所有的维修单位列表
	public List<RepairProviderModel> selectListByAllWithPage(RowBounds rb)throws Exception; 
	//查找所有的维修单位的个数
	public int selectCountByAll() throws Exception;
}
