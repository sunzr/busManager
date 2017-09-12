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

import com.neusoft.busManager.baseinfo.mapper.IBusMapper;
import com.neusoft.busManager.baseinfo.mapper.IBusTypeMapper;
import com.neusoft.busManager.baseinfo.model.BusTypeModel;
import com.neusoft.busManager.baseinfo.service.IBusTypeService;
//车辆类型的业务实现类
@Service("BusTypeService")
@Transactional
public class BusTypeServiceImpl implements IBusTypeService{
       private IBusTypeMapper ibtm=null;
       private IBusMapper ibm=null;
    
       @Autowired  
	public void setIbtm(IBusTypeMapper ibtm) {
		this.ibtm = ibtm;
	}

       @Autowired 
	public void setIbm(IBusMapper ibm) {
		this.ibm = ibm;
	}


	@Override
	public void add(BusTypeModel btm) throws Exception {
		if(btm.getPhotoFileName()!=null){
		   ibtm.insertWithPhoto(btm);
		}
		else{
		   ibtm.insert(btm);
		}
		
	}

	@Override
	public void modify(BusTypeModel btm) throws Exception {
		ibtm.update(btm);
		
		
	}
	@Override
	public void modifyWithPhoto(BusTypeModel btm) throws Exception {
		ibtm.updateWithPhoto(btm);
		
	}

	@Override
	public void modifyForDeletePhoto(BusTypeModel btm) throws Exception {
		ibtm.updateForDeletePhoto(btm);
	}

	@Override
	public void delete(BusTypeModel btm) throws Exception {
		ibtm.delete(btm);
		
	}

	@Override
	public BusTypeModel get(int typeno) throws Exception {
		return ibtm.select(typeno);
	}

	@Override
	public List<BusTypeModel> getListByAll() throws Exception {
	    return ibtm.selectListByAll();
	}

	@Override
	public List<BusTypeModel> getListByAllWithPage(int rows,int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return ibtm.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {
		return ibtm.selectCountByAll();
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
       //检查指定的车辆类型能否被删除
	 public boolean  checkCanDelete(int typeno) throws Exception{
		 boolean result=true;
		 //如果此车辆类型的车辆个数大于0，此车辆类型不能被删除
		 if(ibm.selectCountByCondition(typeno, 0)>0){
			 result=false;
		 }
		 return result;
	 }
	@Override
	public boolean checkNameExist(String typename) throws Exception {
		boolean result=false;
		List<BusTypeModel> list=this.getListByAll();
		for(BusTypeModel bm : list){
			if(bm!=null && bm.getTypename()!=null && bm.getTypename().equals(typename)){
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
		    	int typeno=(int)c0.getNumericCellValue();
		    	Cell c1=row.getCell(1);
		       String typename=c1.getStringCellValue();
		       BusTypeModel btm=new BusTypeModel();
		       btm.setTypeno(typeno);
		       btm.setTypename(typename);
		       this.add(btm);
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
		 
		 //取得所有的车辆类型列表
		 List<BusTypeModel> list=ibtm.selectListByAll();
		  int i=1;
		  for(BusTypeModel bm : list){
			  Row row=sheet.createRow(i);
			  Cell c0=row.createCell(0);
			  c0.setCellValue(bm.getTypeno());
			  Cell c1=row.createCell(1);
			  c1.setCellValue(bm.getTypename());
			  i++;
		  }
	FileOutputStream fileOut =new FileOutputStream(exportFile);
	wb.write(fileOut);
	fileOut.close();
  }

	
}
