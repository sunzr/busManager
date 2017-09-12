package com.neusoft.busManager.baseinfo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.baseinfo.mapper.IBusFactoryMapper;
import com.neusoft.busManager.baseinfo.mapper.IBusMapper;
import com.neusoft.busManager.baseinfo.model.BusFactoryModel;
import com.neusoft.busManager.baseinfo.service.IBusFactoryService;
//车辆厂家的业务实现类
@Service("BusFactoryService")
@Transactional
public class BusFactoryServiceImpl implements IBusFactoryService{
   private IBusFactoryMapper ibfm=null;
   private IBusMapper ibm=null;
@Autowired
public void setIbfm(IBusFactoryMapper ibfm) {
	this.ibfm = ibfm;
}
    @Autowired
	public void setIbm(IBusMapper ibm) {
	this.ibm = ibm;
}

	@Override
	public void add(BusFactoryModel bfm) throws Exception {
		 if(bfm.getPhotoFileName()!=null){
			 ibfm.insertWithPhoto(bfm);
		 }
		 else{
          ibfm.insert(bfm);
		 }
		
	}
		@Override
		public void modify(BusFactoryModel bfm) throws Exception {
			ibfm.update(bfm);
		}
	@Override
	public void modifyWithPhoto(BusFactoryModel bfm) throws Exception {
		ibfm.updateWithPhoto(bfm);
	}
		@Override
		public void modifyForDeletePhoto(BusFactoryModel bfm) throws Exception {
			ibfm.updateForDeletePhoto(bfm);
		}
			@Override
			public void delete(BusFactoryModel bfm) throws Exception {
				ibfm.delete(bfm);
			}
		@Override
		public BusFactoryModel get(int factoryno) throws Exception {
			return ibfm.select(factoryno);
		}
	@Override
	public List<BusFactoryModel> getListByAll() throws Exception {
	return ibfm.selectListByAll();
	}
		@Override
		public List<BusFactoryModel> getListByAllWithPage(int rows, int page) throws Exception {
			RowBounds rb=new RowBounds(rows*(page-1),rows);
			return ibfm.selectListByAllWithPage(rb);
		}
@Override
public int getCountByAll() throws Exception {
	return ibfm.selectCountByAll();
}
	@Override
	public int getPageCountByAll(int rows) throws Exception {
		int pageCount=0;
		int count=this.getCountByAll();
		if(count%rows==0){
			pageCount=count/rows;
		}
		else{
			pageCount=count/rows+1;
		}
		return pageCount;
	}
	
	@Override
	public boolean checkCanDelete(int factoryno) throws Exception {
		 boolean result=true;
		 //如果此车辆厂家的车辆个数大于0，此车辆厂家不能被删除
		 if(ibm.selectCountByCondition(0,factoryno)>0){
			 result=false;
		 }
		 return result;
	}
	@Override
	public boolean checkNameExist(String factoryname) throws Exception {
		boolean result=false;
		List<BusFactoryModel> list=this.getListByAll();
		for(BusFactoryModel bf : list){
			if(bf!=null && bf.getFactoryname()!=null && bf.getFactoryname().equals(factoryname)){
				result=true;
				break;
			}
		}
		return result;
	}
	@Override
	public void importFromExcel(InputStream excelFile) throws Exception {
		//打开上传的excel文件
				Workbook wb=WorkbookFactory.create(excelFile);
				//取得第一个sheet
				Sheet sheet=wb.getSheetAt(0);
				
				    for(Row row : sheet){
				    	if(row.getRowNum()!=0){
				    	Cell c0=row.getCell(0);
				    	int factoryno=(int)c0.getNumericCellValue();
				    	Cell c1=row.getCell(1);
				       String factoryname=c1.getStringCellValue();
				       Cell c2=row.getCell(2);
				       String factorydesc=c2.getStringCellValue();
				       BusFactoryModel bfm=new BusFactoryModel();
				       bfm.setFactoryno(factoryno);
				       bfm.setFactoryname(factoryname);
				       bfm.setFactorydesc(factorydesc);
				       this.add(bfm);
				    }
				 }
			   wb.close();
			   excelFile.close();
	}
	@Override
	public void exportToExcel(File source, File exportFile) throws Exception {
		// 打开excel模板文件
				 Workbook wb=WorkbookFactory.create(source);
				 //取得第一个sheet
				 Sheet sheet =wb.getSheetAt(0);
				 
				 //取得所有的车辆厂家列表
				 List<BusFactoryModel> list=ibfm.selectListByAll();
				  int i=1;
				  for(BusFactoryModel bf : list){
					  Row row=sheet.createRow(i);
					  
					  Cell c0=row.createCell(0);
					  c0.setCellValue(bf.getFactoryno());
					  
					  Cell c1=row.createCell(1);
					  c1.setCellValue(bf.getFactoryname());
					  
					  Cell c2=row.createCell(2);
					  c2.setCellValue(bf.getFactorydesc());
					  i++;
				  }
			FileOutputStream fileOut =new FileOutputStream(exportFile);
			wb.write(fileOut);
			fileOut.close();
		  }
}
	    
