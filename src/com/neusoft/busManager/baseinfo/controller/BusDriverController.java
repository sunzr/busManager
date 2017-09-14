package com.neusoft.busManager.baseinfo.controller;

import java.io.File;
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

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.service.IBusDriverService;
import com.neusoft.busManager.util.ResultMessage;

@RestController
@RequestMapping("/busdriver")
public class BusDriverController {
    private IBusDriverService ibds=null;
    @Autowired
	public void setIbds(IBusDriverService ibds) {
		this.ibds = ibds;
	}
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public ResultMessage add(BusDriverModel bdm,@RequestPart(required=false) MultipartFile uploadphoto,HttpSession session) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   if(uploadphoto!=null && (!uploadphoto.isEmpty())){
	 			String fileName=uploadphoto.getOriginalFilename();
	 			String contentType=uploadphoto.getContentType();
	 			ServletContext application=session.getServletContext();
	 			
	 			String path=application.getRealPath("/upload/"+fileName);
	 			uploadphoto.transferTo(new File(path));
	 			bdm.setPhoto(uploadphoto.getBytes());
	 			bdm.setPhotoFileName(fileName);
	 			bdm.setPhotoContentType(contentType);
	 			
	 		}
		   ibds.add(bdm);
		   result.setResult("Y");
		   result.setMessage("增加司机信息成功");
		   return result;  
	   }
    
    @RequestMapping(value="/modify",method=RequestMethod.POST)
	   public ResultMessage modify(BusDriverModel bdm,@RequestParam String photoOption,@RequestPart(required=false) MultipartFile uploadphoto,HttpSession session) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   if(photoOption.equals("change")){
   			if(uploadphoto!=null&&(!uploadphoto.isEmpty())){
   			String fileName=uploadphoto.getOriginalFilename();
   			String contentType=uploadphoto.getContentType();
   			ServletContext application=session.getServletContext();
   			
   			String path=application.getRealPath("/upload/"+fileName);
   			uploadphoto.transferTo(new File(path));
   			
   			bdm.setPhoto(uploadphoto.getBytes());
   			bdm.setPhotoFileName(fileName);
   			bdm.setPhotoContentType(contentType);
   			ibds.modifyWithPhoto(bdm);
   		}
   			else{
   				ibds.modify(bdm);
   				ibds.modifyForDeletePhoto(bdm);
   			}
   		}
   		else if(photoOption.equals("keep")){
   			ibds.modify(bdm);
   		}
   		else{
   			ibds.modify(bdm);
   			ibds.modifyForDeletePhoto(bdm);
   		}
		   result.setResult("Y");
		   result.setMessage("修改司机信息成功");
		   return result;
	   }
    @RequestMapping(value="/delete",method=RequestMethod.POST)
	   public ResultMessage delete(BusDriverModel bdm) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   ibds.delete(bdm);
		   result.setResult("Y");
		   result.setMessage("删除司机信息成功");
		   return result;
	   } 
  //取得指定的司机信息
	   @RequestMapping(value="/get",method=RequestMethod.GET)
	   public BusDriverModel get(@RequestParam int driverid) throws Exception
	   {
		   return ibds.get(driverid);
	   }
	   //取得所有司机信息列表，不分页
	   @RequestMapping(value="/list/all",method=RequestMethod.GET)
	   public List<BusDriverModel> getListByAll() throws Exception
	   {
		   return ibds.getListByAll();
	   }
	//取得所有司机信息列表，分页方式
	   @RequestMapping(value="/list/page",method=RequestMethod.GET)
	   public List<BusDriverModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   return ibds.getListByAllWithPage(rows, page);
	   }
	   //检查指定的司机信息能否被删除
	   @RequestMapping(value="/checkcandelete",method=RequestMethod.GET)
	   public ResultMessage checkCanDelete(@RequestParam int driverid) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   if(ibds.checkCanDelete(driverid)){
	   			result.setResult("Y");
				result.setMessage("此司机信息可以删除");
	   		}
	   		else{
	   			result.setResult("N");
				result.setMessage("此司机信息不能删除");
	   		}
		return result;
	   }
	   //检查司机身份证号是否存在
	  	 @RequestMapping(value="/checkDcardExist",method=RequestMethod.GET,produces="application/json")
	    public boolean checkDcardExist(@RequestParam String dcard) throws Exception{
	   	 return !ibds.checkDcardExist(dcard);
	    } 
	  //下载文件的方法
	  	@RequestMapping(value="/downphoto",method=RequestMethod.GET)
	  	 public ResponseEntity<byte[]> downloadPhoto(@RequestParam int driverid) throws Exception
	  	 {
	  		 BusDriverModel bdm=ibds.get(driverid);
	  		 String fileName=new String(bdm.getPhotoFileName().getBytes("UTF-8"),"iso-8859-1");
	  		 String contentType=bdm.getPhotoContentType();
	  		 
	  		 String mainType=contentType.substring(0,contentType.indexOf("/"));
	  		 String subType=contentType.substring(contentType.indexOf("/")+1);
	  		 HttpHeaders headers=new HttpHeaders();
	  		 headers.setContentDispositionFormData("attachment",fileName);
	  		 headers.setContentType(new MediaType(mainType,subType));
	  		 return new ResponseEntity<byte[]>(bdm.getPhoto(),headers,HttpStatus.CREATED);
	  		 
	  	 }
}
