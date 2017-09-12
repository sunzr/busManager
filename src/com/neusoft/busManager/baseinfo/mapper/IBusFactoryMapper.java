package com.neusoft.busManager.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.baseinfo.model.BusFactoryModel;

//车辆厂家Mapper接口
public interface IBusFactoryMapper {
   //增加车辆厂家
	public void insert(BusFactoryModel bfm)throws Exception;
	public void insertWithPhoto(BusFactoryModel bfm)throws Exception;
	
	//修改车辆厂家
       public void update(BusFactoryModel bfm) throws Exception;
     //修改车辆厂家,有附件信息处理
       public void updateWithPhoto(BusFactoryModel bfm) throws Exception;
     //删除车辆厂家,有附件信息处理
       public void updateForDeletePhoto(BusFactoryModel bfm) throws Exception;
		//删除车辆厂家
		public void delete(BusFactoryModel bfm) throws Exception;
		
		//取得指定的车辆厂家
		 public BusFactoryModel select(int factoryno)throws Exception;
		//取得所有车辆厂家
			public List<BusFactoryModel> selectListByAll() throws Exception;
		//分页方式取得所有车辆厂家
			public List<BusFactoryModel> selectListByAllWithPage(RowBounds rb)throws Exception;
		//取得车辆厂家的个数
			public int selectCountByAll() throws Exception;
}
