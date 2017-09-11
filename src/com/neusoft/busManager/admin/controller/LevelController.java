package com.neusoft.busManager.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.admin.model.LevelManager;
import com.neusoft.busManager.admin.service.ILevelService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;


@RequestMapping("level")
@RestController
public class LevelController {
	
	@Autowired
	private ILevelService levelService;
	
	//取得指定的模块对象
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public LevelManager get(@RequestParam int lid) throws Exception{
		return levelService.get(lid);
	}
	
	//取得所有的模块对象列表
	@RequestMapping(value="/list/all",method=RequestMethod.GET)
	public List<LevelManager> getListByAll() throws Exception{
		
		return levelService.getListByAll();
	}
	
	
	//取得所有操作员列表，分页模式
	@RequestMapping(value="/list/page",method=RequestMethod.GET)
	public ResultInfo<LevelManager> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception
	{
		ResultInfo<LevelManager> result=new ResultInfo<LevelManager>();
		result.setCount(levelService.getCountByAll());
		result.setPageCount(levelService.getPageCountByAll(rows));
		result.setPage(page);
		result.setRows(rows);
		result.setList(levelService.getListByAllWithPage(rows, page));
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultMessage add(LevelManager mm) throws Exception
	{
		ResultMessage result=new ResultMessage();
		levelService.add(mm);
		result.setResult("Y");
		result.setMessage("增加模块成功");
		return result;
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResultMessage delete(LevelManager um) throws Exception
	{
		ResultMessage result=new ResultMessage();
		levelService.delete(um);
		result.setResult("Y");
		result.setMessage("删除系统模块成功");
		return result;
		
	}
	
	@RequestMapping(value="/levelidvalidate",method=RequestMethod.GET)
	public boolean levelidvalidate(int lid) throws Exception {
		if(levelService.get(lid)!=null)
			return false;
		else
			return true;
	}
	
	@RequestMapping(value="/levelidvalidateByName",method=RequestMethod.GET)
	public boolean levelidvalidate(String lname) throws Exception {
		if(levelService.selectByName(lname)!=null)
			return false;
		else
			return true;
	}
}
