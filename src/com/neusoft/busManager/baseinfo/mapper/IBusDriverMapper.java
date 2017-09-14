package com.neusoft.busManager.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;


//司机信息Mapper类
public interface IBusDriverMapper {
	   //增加司机信息
		public void insert(BusDriverModel bdm) throws Exception;
		public void insertWithPhoto(BusDriverModel bdm) throws Exception;
		 //修改司机信息
			public void update(BusDriverModel bdm) throws Exception;
			 //修改司机信息,有附件信息处理
			public void updateWithPhoto(BusDriverModel bdm) throws Exception;
		//删除司机信息
		public void delete(BusDriverModel bdm) throws Exception;
		 //删除司机信息,有附件信息处理
		public void updateForDeletePhoto(BusDriverModel bdm) throws Exception;
		//取得指定的司机信息
		 public BusDriverModel select(int driverid)throws Exception;
		//取得所有司机信息
			public List<BusDriverModel> selectListByAll() throws Exception;
		//分页方式取得所有的司机信息列表
			public List<BusDriverModel> selectListByAllWithPage(RowBounds rb)throws Exception;
		//取得司机信息的个数
			public int selectCountByAll() throws Exception;
}
