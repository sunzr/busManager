package com.neusoft.busManager.repairinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.repairinfo.model.RepairTypeModel;

public interface IRepairTypeMapper {
		//增加维修类型
		public void insert(RepairTypeModel rtm) throws Exception;
		//修改维修类型
		public void update(RepairTypeModel rtm ) throws Exception;
		//删除维修类型
		public void delete(RepairTypeModel rtm) throws Exception;
		//取得指定的维修类型
		public RepairTypeModel select(int typeNo) throws Exception;
		//取得所有维修类型列表，无分页
		public List<RepairTypeModel> selectListByAll() throws Exception;
		//取得所有维修类型列表，有分页
		public List<RepairTypeModel> selectListByAllWithPage(RowBounds rb) throws Exception;
		//取得维修类型个数
		public int selectCountByAll() throws Exception;

}
