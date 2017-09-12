package com.neusoft.busManager.admin.controller;

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

import com.neusoft.busManager.admin.model.UserModel;
import com.neusoft.busManager.admin.service.IUserService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;
import com.neusoft.busManager.util.UserInfo;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultMessage add(UserModel um,@RequestParam(required=false) int[] functionNos ) throws Exception
	{
		ResultMessage result=new ResultMessage();
		UserModel userModel = userService.get(um.getUserid());

		userService.add(um);
		if(functionNos!=null){
			userService.grantFunctions(um.getUserid(), functionNos);
		}
		result.setResult("Y");
		result.setMessage("增加系统操作员成功");

		return result;

	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public ResultMessage modify(UserModel um,@RequestParam(required=false) int[] functionNos) throws Exception
	{
		ResultMessage result=new ResultMessage();
		userService.modify(um);
		result.setResult("Y");
		result.setMessage("修改系统操作员成功");
		return result;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResultMessage delete(UserModel um) throws Exception
	{
		ResultMessage result=new ResultMessage();
		userService.delete(um);
		result.setResult("Y");
		result.setMessage("删除系统操作员成功");
		return result;
		
	}
	//验证操作员方法,用于操作员登录验证
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public ResultMessage validate(@RequestParam String userid,@RequestParam String password,HttpSession session) throws Exception
	{
		ResultMessage result=new ResultMessage();
		if(userService.validate(userid, password)){
			//将用户保存到会话对象
			UserModel um=userService.get(userid);
			System.out.println(um.getName());
			session.setAttribute("user", um);
			//
			result.setResult("Y");
			result.setMessage("验证系统操作员成功");
		}
		else{
			result.setResult("N");
			result.setMessage("验证系统操作员失败");
		}
		
		return result;
		
	}
	//取得指定的系统操作员
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public UserModel get(@RequestParam String userid) throws Exception
	{
		UserModel userModel = userService.get(userid);
		return userModel;
		
	}
	//取得所有操作员列表，无分页
	@RequestMapping(value="/list/all",method=RequestMethod.GET)
	public List<UserModel> getListByAll() throws Exception
	{
		return userService.getListByAll();
		
	}
	
	//取得所有操作员列表，分页模式(旧方法)
	@RequestMapping(value="/list/page/old",method=RequestMethod.GET)
	public List<UserModel> getListByAllWithPageOld(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception
	{
		return userService.getListByAllWithPage(rows, page);
		
	}
	
	//取得所有操作员列表，分页模式
	@RequestMapping(value="/list/page",method=RequestMethod.GET)
	public ResultInfo<UserModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception
	{
		ResultInfo<UserModel> result=new ResultInfo<UserModel>();
		result.setCount(userService.getCountByAll());
		result.setPageCount(userService.getPageCountByAll(rows));
		result.setPage(page);
		result.setRows(rows);
		result.setList(userService.getListByAllWithPage(rows, page));
		return result;
		
	}
	
//	//取得会话对象中保存的操作员对象
//	@RequestMapping(value="/getfromsession",method=RequestMethod.GET)
//	public UserInfo<UserModel> get(HttpSession session) throws Exception
//	{
//		UserInfo<UserModel> userInfo=new UserInfo<UserModel>();
//		//从会话对象中取得用户信息直接返回
//		UserModel um=(UserModel)session.getAttribute("user");
//		if(um==null){
//			userInfo.setResult("N");
//			userInfo.setMessage("操作员没有登录");
//		}
//		else{
//			userInfo.setResult("Y");
//			userInfo.setMessage("操作员已经登录");
//			userInfo.setUser(um);
//		}
//		System.out.println("取得sesion用户");
//		
//		return userInfo;
//		
//	}
	//检查指定的操作员能否被删除
	@RequestMapping(value="/checkcandelete",method=RequestMethod.GET)
	public ResultMessage checkCanDelete(@RequestParam String userid) throws Exception
	{
		ResultMessage result=new ResultMessage();
		if(userService.checkCanDelete(userid)){
			result.setResult("Y");
			result.setMessage("此操作员可以被删除!");
		}
		else{
			result.setResult("N");
			result.setMessage("此操作员不能被删除!");
		}
		return result;
	}
	
	
	//操作员注销
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResultMessage logout(HttpSession session) throws Exception
	{
		ResultMessage result=new ResultMessage();
		session.invalidate(); //销毁会话对象
		result.setResult("Y");
		result.setMessage("操作员注销成功");
		
		return result;
	}
	//验证用户ID是否存在
	@RequestMapping(value="/useridvalidate",method=RequestMethod.GET)
	public boolean checkUserIdValidate(@RequestParam String userid) throws Exception{
		if(userService.get(userid)!=null){
			return false;
		}
		else{
			return true;
		}
	}
	
	//验证用户ID是否允许登录
	@RequestMapping(value="/usercanlogin",method=RequestMethod.GET)
	public boolean checkUserCanLogin(@RequestParam String userid) throws Exception{
		UserModel um=userService.get(userid);
		if(um!=null) {
			return true;
		}else {
			return false;
		}
	}
	
//	@RequestMapping(value="/loginsetting",method=RequestMethod.POST)
//	public ResultMessage loginStatusSetting(UserModel um) throws Exception
//	{
//		ResultMessage result=new ResultMessage();
//		userService.settingLoginStatus(um);
//		result.setResult("Y");
//		result.setMessage("设置操作员登录状态成功");
//		return result;
//		
//	}
	
	//检查操作员的旧密码是否正确
	@RequestMapping(value="/checkpasswordvalidate",method=RequestMethod.POST)
	public boolean checkPasswordValidate(@RequestParam String userid,@RequestParam String password) throws Exception{
		return userService.validate(userid, password);
	}
	
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public ResultMessage changePassword(UserModel um) throws Exception
	{
		ResultMessage result=new ResultMessage();
		userService.changePassword(um);
		result.setResult("Y");
		result.setMessage("操作员密码修改成功");
		
		return result;
	}
	
	@RequestMapping(value="/selectWithoutFunction")
	public List<UserModel> selectWithoutFunction(@RequestParam String funno){
		List<UserModel> users = userService.selectWithoutFunction(funno);
		return users;
	}
		
	@RequestMapping(value="/getfromsession",method=RequestMethod.GET)
	public UserInfo<UserModel> getFromSession(HttpSession session){
		UserInfo<UserModel> users=new UserInfo<>();
		UserModel userModel= (UserModel) session.getAttribute("user");
		if(userModel==null) {
			users.setMessage("该用户不存在");
			users.setResult("N");
			return users;
		}else {
			users.setMessage("操作员已登录");
			users.setResult("Y");
			users.setUser(userModel);
			return users;
		}
	}
	
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultMessage importFromExcel(@RequestPart MultipartFile importfile) throws Exception
	{
		 ResultMessage result=new ResultMessage();
		 if(importfile!=null&&(!importfile.isEmpty())){
			 userService.importFromExcel(importfile.getInputStream());
			 result.setResult("Y");
			 result.setMessage("导入操作员成功");
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
		String sourcepath=application.getRealPath("/excelexport/userexport.xlsx");
		String exportfilepath=application.getRealPath("/download/exportexcel"+(int)(Math.random()*1000)+".xlsx");
		
		
		userService.exportToExcel(new File(sourcepath),new File(exportfilepath));
		
		String mainType="application";
		
		String subType="vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String fileName=new String("操作员导出.xlsx".getBytes("UTF-8"),"iso-8859-1");
		
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
