package com.neusoft.busManager.queryinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.queryinfo.model.IllegalTypeModel;
import com.neusoft.busManager.queryinfo.service.IIllegalTypeService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

@RestController
@RequestMapping("/illegalType")
public class IllegalTypeController {
	
	@Autowired
	private IIllegalTypeService illegalTypeService;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public IllegalTypeModel get(@RequestParam int typeno) throws Exception{
		return illegalTypeService.get(typeno);
	}
	
	@RequestMapping(value="/list/all",method=RequestMethod.GET)
	public List<IllegalTypeModel> getListByAll() throws Exception{
		
		return illegalTypeService.getListByAll();
	}
	
	
	@RequestMapping(value="/list/page",method=RequestMethod.GET)
	public ResultInfo<IllegalTypeModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception
	{
		ResultInfo<IllegalTypeModel> result=new ResultInfo<IllegalTypeModel>();
		result.setCount(illegalTypeService.getCountByAll());
		result.setPageCount(illegalTypeService.getPageCountByAll(rows));
		result.setPage(page);
		result.setRows(rows);
		result.setList(illegalTypeService.getListByAllWithPage(rows, page));
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultMessage add(IllegalTypeModel mm) throws Exception
	{
		ResultMessage result=new ResultMessage();
		illegalTypeService.add(mm);
		result.setResult("Y");
		result.setMessage("增加模块成功");
		return result;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResultMessage delete(IllegalTypeModel um) throws Exception
	{
		ResultMessage result=new ResultMessage();
		illegalTypeService.delete(um);
		result.setResult("Y");
		result.setMessage("删除系统模块成功");
		return result;
		
	}
	
	@RequestMapping(value="/illegalTypedvalidate",method=RequestMethod.GET)
	public boolean levelidvalidate(int lid) throws Exception {
		if(illegalTypeService.get(lid)!=null)
			return false;
		else
			return true;
	}
	
	@RequestMapping(value="/illegalTypedvalidateByName",method=RequestMethod.GET)
	public boolean levelidvalidate(String lname) throws Exception {
		if(illegalTypeService.selectByName(lname)!=null)
			return false;
		else
			return true;
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public ResultMessage modify(IllegalTypeModel model,@RequestParam(required=false) int[] functionNos) throws Exception
	{
		ResultMessage result=new ResultMessage();
		illegalTypeService.modify(model);
		result.setResult("Y");
		result.setMessage("修改违章类型成功");
		return result;
		
	}
}
