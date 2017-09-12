package com.neusoft.busManager.repairinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.repairinfo.model.RepairProviderModel;
import com.neusoft.busManager.repairinfo.service.IRepairProviderService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//维修单位控制器
@RestController
@RequestMapping(value="/repairprovider")
public class RepairProviderController {
	@Autowired
	private IRepairProviderService irps;
	@RequestMapping(value="/add",method={RequestMethod.POST})
	public ResultMessage add(RepairProviderModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("增加维修单位失败");
		irps.add(model);
		result.setResult("Y");
		result.setMessage("增加维修单位成功");
		return result;
	}

	@RequestMapping(value="/modify",method={RequestMethod.POST})
	public ResultMessage modify(RepairProviderModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("修改维修单位失败");
		irps.modify(model);
		result.setResult("Y");
		result.setMessage("修改维修单位成功");
		return result;
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public ResultMessage delete(RepairProviderModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("删除维修单位失败");
		irps.delete(model);
		result.setResult("Y");
		result.setMessage("删除维修单位成功");
		return result;
	}
	//取得指定的维修单位
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public RepairProviderModel get(@RequestParam int providerNo) throws Exception{
		return irps.get(providerNo);
	}
	//取得所有的维修单位列表
	@RequestMapping(value="/list/all",method=RequestMethod.GET,produces="application/json")
	public List<RepairProviderModel> getListByAll() throws Exception{
		return irps.getListByAll();
	}
	//以分页的方式取得所有的维修单位列表
	@RequestMapping(value="/list/page",method=RequestMethod.GET,produces="application/json")
	public ResultInfo<RepairProviderModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="5")int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception{
		int count=irps.getCountByAll();
		int pageCount=irps.getPageCountByAll(rows);
		List<RepairProviderModel> list=irps.getListByAllWithPage(rows, page);
		ResultInfo<RepairProviderModel> result=new ResultInfo<RepairProviderModel>();
		result.setCount(count);
		result.setPage(page);
		result.setPageCount(pageCount);
		result.setRows(rows);
		result.setList(list);
		return result;
	} 
	//重名
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam int providerNo) throws Exception{
		if(irps.get(providerNo)!=null){
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
