package com.neusoft.busManager.repairinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.repairinfo.model.RepairTypeModel;
import com.neusoft.busManager.repairinfo.service.IRepairTypeService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//维修类型控制器类
@RestController
@RequestMapping(value="/repairtype")
public class RepairTypeController {
	
	@Autowired
	private IRepairTypeService its;
	
	@RequestMapping(value="/add",method={RequestMethod.POST})
	public ResultMessage add(RepairTypeModel model) throws Exception{
		ResultMessage result =new ResultMessage();
		result.setMessage("增加维修类型失败");
		its.add(model);
		result.setResult("Y");
		result.setMessage("增加维修类型成功");
		return result;
	}
	
	@RequestMapping(value="/modify",method={RequestMethod.POST})
	public ResultMessage modify(RepairTypeModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("修改维修类型失败");
		its.modify(model);
		result.setResult("Y");
		result.setMessage("修改维修类型成功");
		return result;
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public ResultMessage delete(RepairTypeModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("删除维修类型失败");
		its.delete(model);
		result.setResult("Y");
		result.setMessage("删除维修类型成功");
		return result;
	}
	//取得指定的维修类型
	@RequestMapping(value="/get",method=RequestMethod.GET,produces="application/json")
	public RepairTypeModel get(@RequestParam int typeNo) throws Exception{
		return its.get(typeNo);
	}
	//取得所有的维修类型列表
	@RequestMapping(value="/list/all",method=RequestMethod.GET,produces="application/json")
	public List<RepairTypeModel> getListByAll() throws Exception{
		return its.getListByAll();
	}
	//以分页的方式取得所有的维修类型列表
	@RequestMapping(value="/list/page",method=RequestMethod.GET,produces="application/json")
	public ResultInfo<RepairTypeModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="5")int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception{
		int count=its.getCountByAll();
		int pageCount=its.getPageCountByAll(rows);
		List<RepairTypeModel> list=its.getListByAllWithPage(rows, page);
		ResultInfo<RepairTypeModel> result=new ResultInfo<RepairTypeModel>();
		result.setCount(count);
		result.setPage(page);
		result.setPageCount(pageCount);
		result.setRows(rows);
		result.setList(list);
		return result;
	} 
	//重名
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam int typeNo) throws Exception{
		if(its.get(typeNo)!=null){
			return false;
		}
		return true;
	}
	//是否可以被删除
	@RequestMapping(value="/checkCanDelete",method=RequestMethod.GET,produces="application/json")
	public boolean checkCanDelete() throws Exception{
		return true;
	}
	
	
	
	
	
}
