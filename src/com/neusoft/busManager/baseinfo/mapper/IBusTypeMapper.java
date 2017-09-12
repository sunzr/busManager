package com.neusoft.busManager.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.Alias;

import com.neusoft.busManager.baseinfo.model.BusTypeModel;

//车辆类型Mapper接口
public interface IBusTypeMapper {
   //增加车辆类型
	public void insert(BusTypeModel btm) throws Exception;
	public void insertWithPhoto(BusTypeModel btm) throws Exception;
	 //修改车辆类型
		public void update(BusTypeModel btm) throws Exception;
		//修改车辆类型,有附件信息处理
		public void updateWithPhoto(BusTypeModel btm) throws Exception;
		//删除车辆类型,有附件信息处理
		public void updateForDeletePhoto(BusTypeModel btm) throws Exception;
	//删除车辆类型
	public void delete(BusTypeModel btm) throws Exception;
	
	//取得指定的车辆类型
	 public BusTypeModel select(int typeno)throws Exception;
	//取得所有车辆类型
		public List<BusTypeModel> selectListByAll() throws Exception;
	//分页方式取得所有车辆类型
		public List<BusTypeModel> selectListByAllWithPage(RowBounds rb)throws Exception;
	//取得车辆类型的个数
		public int selectCountByAll() throws Exception;
	
}
