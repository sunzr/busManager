package com.neusoft.busManager.baseinfo.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.baseinfo.model.BusFactoryModel;

//车辆厂家业务接口
public interface IBusFactoryService {
	//增加车辆厂家
		public void add(BusFactoryModel bfm)throws Exception;
		//修改车辆厂家
	       public void modify(BusFactoryModel bfm) throws Exception;
	  //修改车辆厂家,,有附件信息处理
	    public void modifyWithPhoto(BusFactoryModel bfm) throws Exception;
	 //删除车辆厂家,,有附件信息处理
	    public void modifyForDeletePhoto(BusFactoryModel bfm) throws Exception;
			//删除车辆厂家
			public void delete(BusFactoryModel bfm) throws Exception;
			
			//取得指定的车辆厂家
			 public BusFactoryModel get(int factoryno)throws Exception;
			//取得所有车辆厂家
				public List<BusFactoryModel> getListByAll() throws Exception;
			//分页方式取得所有车辆厂家
				public List<BusFactoryModel> getListByAllWithPage(int rows,int page)throws Exception;
			//取得车辆厂家的个数
				public int getCountByAll() throws Exception;
				
			//取得车辆厂家的页数
				public int getPageCountByAll(int rows)throws Exception;
				 //检查指定车辆厂家是否可以被删除
				public boolean checkCanDelete(int factoryno) throws Exception;
		   //检查车辆厂家名称是否存在
				public boolean checkNameExist(String factoryname) throws Exception;
		  //从Excel文件导入车辆厂家信息
				public void importFromExcel(InputStream excelFile) throws Exception;
		//将车辆厂家信息写到Excel文件中
				public void exportToExcel(File source,File exportFile) throws Exception;
}
