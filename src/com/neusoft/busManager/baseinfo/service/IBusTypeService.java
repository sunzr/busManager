package com.neusoft.busManager.baseinfo.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.baseinfo.model.BusTypeModel;
//车辆类型的service业务接口
public interface IBusTypeService {
	     //增加车辆类型
		public void add(BusTypeModel btm) throws Exception;
			
		 //修改车辆类型
			public void modify(BusTypeModel btm) throws Exception;
			
		//修改车辆类型,有附件信息处理
		public void modifyWithPhoto(BusTypeModel btm) throws Exception;
		//删除车辆类型,有附件信息处理
		public void modifyForDeletePhoto(BusTypeModel btm) throws Exception;
		//删除车辆类型
		public void delete(BusTypeModel btm) throws Exception;
		
		//取得指定的车辆类型
		 public BusTypeModel get(int typeno)throws Exception;
		//取得所有车辆类型
			public List<BusTypeModel> getListByAll() throws Exception;
		//分页方式取得所有车辆类型
			public List<BusTypeModel> getListByAllWithPage(int rows,int page)throws Exception;
		//取得车辆类型的个数
			public int getCountByAll() throws Exception;
		//取得车辆类型的页数
			public int getPageCountByAll(int rows)throws Exception;
	   //检查指定车辆类型是否可以被删除
			public boolean checkCanDelete(int typeno) throws Exception;
	   //检查车辆类型名称是否存在
			public boolean checkNameExist(String typename) throws Exception;
	  //从Excel文件导入车辆类型信息
			public void importFromExcel(InputStream excelFile) throws Exception;
	//将车辆类型信息写到Excel文件中
			public void exportToExcel(File source,File exportFile) throws Exception;
}
