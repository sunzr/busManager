package com.neusoft.busManager.queryinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusDayInfoService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//车辆日运行信息的控制器类
@RestController
@RequestMapping(value="/busdayinfo")
public class BusDayInfoController {
    private IBusDayInfoService ibds=null;
    @Autowired
	public void setIbds(IBusDayInfoService ibds) {
		this.ibds = ibds;
	}
    @RequestMapping(value="/add",method=RequestMethod.POST)
	   public ResultMessage add(BusDayInfoModel bim) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   ibds.add(bim);
		   result.setResult("Y");
		   result.setMessage("增加车辆日运行信息成功");
		   return result;  
	   }
    @RequestMapping(value="/modify",method=RequestMethod.POST)
	   public ResultMessage modify(BusDayInfoModel bim) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   ibds.modify(bim);
		   result.setResult("Y");
		   result.setMessage("修改车辆日运行信息成功");
		   return result;
	   }
    @RequestMapping(value="/delete",method=RequestMethod.POST)
	   public ResultMessage delete(BusDayInfoModel bim) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   ibds.delete(bim);
		   result.setResult("Y");
		   result.setMessage("删除车辆日运行信息成功");
		   return result;
	   } 
     //取得指定的车辆日运行信息
	   @RequestMapping(value="/get",method=RequestMethod.GET)
	   public BusDayInfoModel get(@RequestParam int infono) throws Exception
	   {
		   return ibds.get(infono);
	   }
	   //取得所有车辆列表，不分页
	   @RequestMapping(value="/list/all",method=RequestMethod.GET)
	   public List<BusDayInfoModel> getListByAll() throws Exception
	   {
		   return ibds.getListByAll();
	   }
	 //按检索条件取得所有车辆日运行信息
	   @RequestMapping(value="/list/condition",method=RequestMethod.POST)
	   public List<BusDayInfoModel> gettListByCondition(@RequestParam(required=false)int busid,
			   @RequestParam(required=false)int driverid) throws Exception{
		   return ibds.gettListByCondition(busid, driverid);
	   }
	 //取得所有车辆日运行信息，分页方式
	   @RequestMapping(value="/list/page",method=RequestMethod.GET)
	   public List<BusDayInfoModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   return ibds.getListByAllWithPage(rows, page);
	   }  
	 //按检索条件取得车辆日运行信息列表，有分页
	   @RequestMapping(value="/list/condition/page00",method=RequestMethod.POST)
	   public List<BusDayInfoModel> getListByConditionWithPage00(
			   @RequestParam(required=false) int busid, 
			   @RequestParam(required=false) int driverid,
			   @RequestParam(required=false,defaultValue="10") int rows,
			   @RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		  
		return  ibds.getListByConditionWithPage(busid,driverid, rows, page);
	   }  
	 //按检索条件取得车辆日运行信息列表，有分页,一次性返回分页的所有信息
	   @RequestMapping(value="/list/condition/page",method=RequestMethod.POST)
	   public ResultInfo<BusDayInfoModel> getListByConditionWithPage(
			   @RequestParam(required=false) int busid, 
			   @RequestParam(required=false) int driverid,
			   @RequestParam(required=false,defaultValue="10") int rows,
			   @RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   ResultInfo<BusDayInfoModel> result=new ResultInfo<BusDayInfoModel>();
		   result.setCount(ibds.getCountByCondition(busid,driverid));
		   result.setPageCount(ibds.getPageCountByCondition(busid,driverid, rows));
		   result.setRows(rows);
		   result.setPage(page);
		   result.setList(ibds.getListByConditionWithPage(busid,driverid, rows, page));
		   return result;
	   }
	  
}
