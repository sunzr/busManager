package com.neusoft.busManager.baseinfo.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
//司机信息的service业务接口
public interface IBusDriverService {
	     //增加司机信息
			public void add(BusDriverModel bdm) throws Exception;
			 //修改司机信息
				public void modify(BusDriverModel bdm) throws Exception;
		 //修改司机信息,有附件信息处理
		public void modifyWithPhoto(BusDriverModel bdm) throws Exception;	
			//删除司机信息
			public void delete(BusDriverModel bdm) throws Exception;
			
			//删除司机信息,有附件信息处理
			public void modifyForDeletePhoto(BusDriverModel bdm) throws Exception;
			
			//取得指定的司机信息
			 public BusDriverModel get(String driverid)throws Exception;
			//取得所有司机信息
				public List<BusDriverModel> getListByAll() throws Exception;
			//分页方式取得所有的司机信息列表
				public List<BusDriverModel> getListByAllWithPage(int rows,int page)throws Exception;
			//取得司机信息的个数
				public int getCountByAll() throws Exception;
		//取得司机信息的页数
		public int getPageCountByAll(int rows)throws Exception;
		
		 //检查司机身份证号是否存在
		public boolean checkDcardExist(String dcard) throws Exception;
}
