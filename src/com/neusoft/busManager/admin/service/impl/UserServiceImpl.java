package com.neusoft.busManager.admin.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.busManager.admin.mapper.IFunctionMapper;
import com.neusoft.busManager.admin.mapper.IUserMapper;
import com.neusoft.busManager.admin.model.FunctionModel;
import com.neusoft.busManager.admin.model.UserModel;
import com.neusoft.busManager.admin.service.IUserService;

@Service("UserService")
@Transactional
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserMapper usermapper;
	@Autowired
	private IFunctionMapper functionMapper;
	
	@Override
	public void add(UserModel um) throws Exception {
		usermapper.insert(um);
	}

	@Override
	public void modify(UserModel um) throws Exception {
		usermapper.update(um);
	}

	@Override
	public void delete(UserModel um) throws Exception {
		usermapper.delete(um);
	}

	@Override
	public UserModel get(String userid) throws Exception {
		return usermapper.select(userid);
	}

	@Override
	public List<UserModel> getListByAll() throws Exception {
		return usermapper.selectListByAll();
	}

	@Override
	public List<UserModel> getListByAllWithPage(int rows, int page) throws Exception {
		RowBounds rb=new RowBounds(rows*(page-1),rows);
		return usermapper.selectListByAllWithPage(rb);
	}

	@Override
	public int getCountByAll() throws Exception {
		
		return usermapper.selectCountByAll();
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
	public boolean validate(String userid, String password) throws Exception {
		boolean result=false;
		UserModel um=usermapper.select(userid);
		if(um!=null&&um.getPassword()!=null&&um.getPassword().equals(password)){
			result=true;
		}
		return result;
	}

	@Override
	public boolean checkCanLogin(String userid) throws Exception {
		return true;
	}

	@Override
	public boolean checkUserExist(String userid) throws Exception {
		return false;
	}

	@Override
	public void changePassword(UserModel um) throws Exception {
		usermapper.updateForPasswowrd(um);
	}

	@Override
	public boolean checkCanDelete(String userid) {
		return true;
	}

	@Override
	public List<UserModel> selectWithoutFunction(String funno) {
		
		return usermapper.selectWithoutFunction(funno);
	}

	@Override
	public void grantFunction(String userid, int functionNo) throws Exception {
		usermapper.grantFunction(userid, functionNo);
	}

	@Override
	public void grantFunctions(String userid, int[] functionNos) throws Exception {
		usermapper.grantFunctions(userid, functionNos);
	}

	//取得指定员工的功能列表
	@Override
	public List<FunctionModel> getFunctionsByUser(String userid) throws Exception {
		
		return functionMapper.selectListByUser(userid);
	}

	@Override
	public void importFromExcel(InputStream excelFile) throws Exception {
		//打开上传的excel文件
		Workbook wb = WorkbookFactory.create(excelFile);
		//取得第1个sheet
		Sheet sheet=wb.getSheetAt(0);
		for (Row row : sheet) {
            if(row.getRowNum()!=0){
            	Cell c0=row.getCell(0);
            	int userid=(int) c0.getNumericCellValue();
            	Cell c1=row.getCell(1);
            	int password=(int) c1.getNumericCellValue();
            	Cell c2=row.getCell(2);
            	String name=c2.getStringCellValue();


            	UserModel user=new UserModel();
            	user.setUserid(String.valueOf(userid));
            	user.setPassword(String.valueOf(password));
            	user.setName(name);
            	this.add(user);
            }
            
        }
		wb.close();
		excelFile.close();
	}

	@Override
	public void exportToExcel(File source, File exportFile) throws Exception {
		//打开excel模板文件
		Workbook wb = WorkbookFactory.create(source);
		//取得第1个sheet
		Sheet sheet=wb.getSheetAt(0);
		//取得所有的小区列表
		List<UserModel> userList=usermapper.selectListByAll();

		
		int i=1;
		for(UserModel user:userList){
			Row row = sheet.createRow(i);
			Cell c0 = row.createCell(0);
			c0.setCellValue(user.getUserid());
			
			Cell c1 = row.createCell(1);
			c1.setCellValue(user.getPassword());

			Cell c2 = row.createCell(2);
			c2.setCellValue(user.getName());
			i++;
		}
		
		FileOutputStream fileOut = new FileOutputStream(exportFile);
		wb.write(fileOut);
		fileOut.close();
		
		wb.close();
		}
		
		

	public void revoleFunctions(String userid) {
		usermapper.revoleFunctions(userid);		
	}
}
