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

import com.neusoft.busManager.baseinfo.model.BusFactoryModel;
import com.neusoft.busManager.baseinfo.service.IBusFactoryService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//车辆厂家控制器
@RestController
@RequestMapping("/busfactory")
public class BusFactoryController {
      private IBusFactoryService ibfs=null;
    @Autowired
	public void setIbfs(IBusFactoryService ibfs) {
		this.ibfs = ibfs;
	}
     
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResultMessage add(BusFactoryModel bfm,@RequestPart(required=false) MultipartFile uploadphoto,HttpSession session)throws Exception
    {
   	 ResultMessage result=new ResultMessage();
   	 if(uploadphoto!=null && (!uploadphoto.isEmpty())){
			String fileName=uploadphoto.getOriginalFilename();
			String contentType=uploadphoto.getContentType();
			ServletContext application=session.getServletContext();
			
			String path=application.getRealPath("/upload/"+fileName);
			uploadphoto.transferTo(new File(path));
			bfm.setPhoto(uploadphoto.getBytes());
			bfm.setPhotoFileName(fileName);
			bfm.setPhotoContentType(contentType);
			
		}
   	 ibfs.add(bfm);
   	 result.setResult("Y");
   	 result.setMessage("增加车辆厂家成功");
   	 return result;
    }
    
    @RequestMapping(value="/modify",method=RequestMethod.POST)
    public ResultMessage modify(BusFactoryModel bfm,@RequestParam String photoOption,@RequestPart(required=false) MultipartFile uploadphoto,HttpSession session)throws Exception
    {
   	 ResultMessage result=new ResultMessage();
   	 if(photoOption.equals("change")){
   			if(uploadphoto!=null&&(!uploadphoto.isEmpty())){
   			String fileName=uploadphoto.getOriginalFilename();
   			String contentType=uploadphoto.getContentType();
   			ServletContext application=session.getServletContext();
   			
   			String path=application.getRealPath("/upload/"+fileName);
   			uploadphoto.transferTo(new File(path));
   			
   			bfm.setPhoto(uploadphoto.getBytes());
   			bfm.setPhotoFileName(fileName);
   			bfm.setPhotoContentType(contentType);
   			ibfs.modifyWithPhoto(bfm);
   		}
   			else{
   				ibfs.modify(bfm);
   				ibfs.modifyForDeletePhoto(bfm);
   			}
   		}
   		else if(photoOption.equals("keep")){
   			ibfs.modify(bfm);
   		}
   		else{
   			ibfs.modify(bfm);
   			ibfs.modifyForDeletePhoto(bfm);
   		}
   	 result.setResult("Y");
   	 result.setMessage("修改车辆厂家成功");
   	 return result;
    }
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public ResultMessage delete(BusFactoryModel bfm)throws Exception
    {
   	 ResultMessage result=new ResultMessage();
   	 ibfs.delete(bfm);
   	 result.setResult("Y");
   	 result.setMessage("删除车辆厂家成功");
   	 return result;
    }
    
    @RequestMapping(value="/get",method=RequestMethod.GET)
    public BusFactoryModel get(@RequestParam int factoryno)throws Exception
    {
   	    return ibfs.get(factoryno);
    }
    //取得所有 车辆厂家列表，不分页
    @RequestMapping(value="/list/all",method=RequestMethod.GET)
    public List<BusFactoryModel> getListByAll()throws Exception
    {
   	 return ibfs.getListByAll();
    }
    //取得所有 车辆厂家列表，分页方式
    @RequestMapping(value="/list/page",method=RequestMethod.GET)
    public ResultInfo<BusFactoryModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10")int rows,@RequestParam(required=false,defaultValue="1")int page)throws Exception
    {
   	 ResultInfo<BusFactoryModel> result=new ResultInfo<BusFactoryModel>();
   	 result.setCount(ibfs.getCountByAll());
   	 result.setPageCount(ibfs.getPageCountByAll(rows));
   	 result.setRows(rows);
   	 result.setPage(page);
   	 result.setList(ibfs.getListByAllWithPage(rows, page));
   	 return result;
    }
  //检查指定的车辆类型能否被删除
    @RequestMapping(value="/checkcandelete",method=RequestMethod.GET,produces="application/json")
  	 public ResultMessage checkCanDelete(@RequestParam int factoryno) throws Exception{
  		ResultMessage result=new ResultMessage();
  		if(ibfs.checkCanDelete(factoryno)){
  			result.setResult("Y");
			result.setMessage("此车辆厂家可以删除");
  		}
  		else{
  			result.setResult("N");
			result.setMessage("此车辆厂家不能删除");
  		}
  		 return result;
  	 }  
    //检查车辆厂家名称是否存在
  	 @RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
    public boolean checkNameExist(@RequestParam String factoryname) throws Exception{
   	 return !ibfs.checkNameExist(factoryname);
    }
  	 //下载文件的方法
  	@RequestMapping(value="/downphoto",method=RequestMethod.GET)
  	public ResponseEntity<byte[]> downloadPhoto(@RequestParam int factoryno) throws Exception
  	{
  		BusFactoryModel bfm=ibfs.get(factoryno);
  		String fileName=new String(bfm.getPhotoFileName().getBytes("UTF-8"),"iso-8859-1");
  		String contentType=bfm.getPhotoContentType();
  	
  		String mainType=contentType.substring(0,contentType.indexOf("/"));
  		
  		String subType=contentType.substring(contentType.indexOf("/")+1);
  
  		HttpHeaders headers=new HttpHeaders();
  		headers.setContentDispositionFormData("attachment", fileName);
  		headers.setContentType(new MediaType(mainType,subType));
  		return new ResponseEntity<byte[]>(bfm.getPhoto(),headers,HttpStatus.CREATED);
  	} 
  	
  	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 ibfs.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入车辆厂家成功");
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
		String sourcepath=application.getRealPath("/excelexport/busfactoryexport.xlsx");
		String exportfilepath=application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		
		
		ibfs.exportToExcel(new File(sourcepath),new File(exportfilepath));
		
		String mainType="application";
		
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("车辆厂家导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
		
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
