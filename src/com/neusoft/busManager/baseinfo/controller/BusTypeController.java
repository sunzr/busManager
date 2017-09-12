package com.neusoft.busManager.baseinfo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.busManager.baseinfo.model.BusTypeModel;
import com.neusoft.busManager.baseinfo.service.IBusTypeService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//车辆类型控制器
@RestController
@RequestMapping(value="/bustype")
public class BusTypeController {
   private IBusTypeService ibts=null;
     @Autowired
	public void setIbts(IBusTypeService ibts) {
		this.ibts = ibts;
	}
     @RequestMapping(value="/add",method=RequestMethod.POST)
     public ResultMessage add(BusTypeModel btm,@RequestPart(required=false) MultipartFile uploadphoto,HttpSession session)throws Exception
     {
    	 ResultMessage result=new ResultMessage();
    	 if(uploadphoto!=null && (!uploadphoto.isEmpty())){
 			String fileName=uploadphoto.getOriginalFilename();
 			String contentType=uploadphoto.getContentType();
 			ServletContext application=session.getServletContext();
 			
 			String path=application.getRealPath("/upload/"+fileName);
 			uploadphoto.transferTo(new File(path));
 			btm.setPhoto(uploadphoto.getBytes());
 			btm.setPhotoFileName(fileName);
 			btm.setPhotoContentType(contentType);
 			
 		}
    	 ibts.add(btm);
    	 result.setResult("Y");
    	 result.setMessage("增加车辆类型成功");
    	 return result;
     }
     @RequestMapping(value="/modify",method=RequestMethod.POST)
     public ResultMessage modify(BusTypeModel btm,@RequestParam String photoOption,@RequestPart(required=false) MultipartFile uploadphoto,HttpSession session)throws Exception
     {
    	 ResultMessage result=new ResultMessage();
    	 if(photoOption.equals("change")){
    			if(uploadphoto!=null&&(!uploadphoto.isEmpty())){
    			String fileName=uploadphoto.getOriginalFilename();
    			String contentType=uploadphoto.getContentType();
    			ServletContext application=session.getServletContext();
    			
    			String path=application.getRealPath("/upload/"+fileName);
    			uploadphoto.transferTo(new File(path));
    			
    			btm.setPhoto(uploadphoto.getBytes());
    			btm.setPhotoFileName(fileName);
    			btm.setPhotoContentType(contentType);
    			ibts.modifyWithPhoto(btm);
    		}
    			else{
    				ibts.modify(btm);
    				ibts.modifyForDeletePhoto(btm);
    			}
    		}
    		else if(photoOption.equals("keep")){
    			ibts.modify(btm);
    		}
    		else{
    			ibts.modify(btm);
    			ibts.modifyForDeletePhoto(btm);
    		}
    	 result.setResult("Y");
    	 result.setMessage("修改车辆类型成功");
    	 return result;
     }
     @RequestMapping(value="/delete",method=RequestMethod.POST)
     public ResultMessage delete(BusTypeModel btm)throws Exception
     {
    	 ResultMessage result=new ResultMessage();
    	 ibts.delete(btm);
    	 result.setResult("Y");
    	 result.setMessage("删除车辆类型成功");
    	 return result;
     }
     @RequestMapping(value="/get",method=RequestMethod.GET)
     public BusTypeModel get(@RequestParam int typeno)throws Exception
     {
    	 return ibts.get(typeno);
     }
     //取得所有 车辆类型列表，不分页
     @RequestMapping(value="/list/all",method=RequestMethod.GET)
     public List<BusTypeModel> getListByAll()throws Exception
     {
    	 return ibts.getListByAll();
     }
     //取得所有 车辆类型列表，分页方式
     @RequestMapping(value="/list/page",method=RequestMethod.GET)
     public ResultInfo<BusTypeModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10")int rows,@RequestParam(required=false,defaultValue="1")int page)throws Exception
     {
    	 ResultInfo<BusTypeModel> result=new ResultInfo<BusTypeModel>();
    	 result.setCount(ibts.getCountByAll());
    	 result.setPageCount(ibts.getPageCountByAll(rows));
    	 result.setRows(rows);
    	 result.setPage(page);
    	 result.setList(ibts.getListByAllWithPage(rows, page));
    	 return result;
     }
     
   //检查指定的车辆类型能否被删除
     @RequestMapping(value="/checkcandelete",method=RequestMethod.GET,produces="application/json")
   	 public ResultMessage checkCanDelete(@RequestParam int typeno) throws Exception{
   		ResultMessage result=new ResultMessage();
   		if(ibts.checkCanDelete(typeno)){
   			result.setResult("Y");
			result.setMessage("此车辆类型可以删除");
   		}
   		else{
   			result.setResult("N");
			result.setMessage("此车辆类型不能删除");
   		}
   		 return result;
   	 }
   	 
     //检查车辆类型名称是否存在
   	 @RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
     public boolean checkNameExist(@RequestParam String typename) throws Exception{
    	 return !ibts.checkNameExist(typename);
     }
   	 
   //下载文件的方法
 	@RequestMapping(value="/downphoto",method=RequestMethod.GET)
 	public ResponseEntity<byte[]> downloadPhoto(@RequestParam int typeno) throws Exception
 	{
 		BusTypeModel btm=ibts.get(typeno);
 		String fileName=new String(btm.getPhotoFileName().getBytes("UTF-8"),"iso-8859-1");
 		String contentType=btm.getPhotoContentType();
 	
 		String mainType=contentType.substring(0,contentType.indexOf("/"));
 		
 		String subType=contentType.substring(contentType.indexOf("/")+1);
 
 		HttpHeaders headers=new HttpHeaders();
 		headers.setContentDispositionFormData("attachment", fileName);
 		headers.setContentType(new MediaType(mainType,subType));
 		return new ResponseEntity<byte[]>(btm.getPhoto(),headers,HttpStatus.CREATED);
 	}
 	
 	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 ibts.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入车辆类型成功");
		 }
		 else{
			 result.setResult("N");
			 result.setMessage("没有上传导入Excel文件");
		 }
		 return result;
	}
 	
 	@RequestMapping(value="/exporttoexcel",method=RequestMethod.GET)
	public ResponseEntity<byte[]>  exportToExcel(HttpSession session) throws Exception
	{
		ServletContext application=session.getServletContext();
		String sourcepath=application.getRealPath("/excelexport/bustypeexport.xlsx");
		String exportfilepath=application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		
		
		ibts.exportToExcel(new File(sourcepath),new File(exportfilepath));
		
		String mainType="application";
		
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("车辆类型导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
		
		InputStream in=new FileInputStream(exportfilepath);
		byte[] data=new byte[in.available()];
		in.read(data, 0, data.length);
		in.close();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(new MediaType(mainType,subType));
		File excelFile=new File(exportfilepath);
		excelFile.delete();
		
		return new ResponseEntity<byte[]>(data,headers,HttpStatus.CREATED);
		
	}
}
