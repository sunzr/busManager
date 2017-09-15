package com.neusoft.busManager.queryinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.queryinfo.model.BusIllegalFeeModel;
import com.neusoft.busManager.queryinfo.service.IBusIllegalFeeService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//车辆违章控制器
@RestController
@RequestMapping("/busillegalfee")
public class BusIllegalFeeController {
   private IBusIllegalFeeService ibfs=null;
@Autowired
public void setIbfs(IBusIllegalFeeService ibfs) {
	this.ibfs = ibfs;
}
@RequestMapping(value="/add",method=RequestMethod.POST)
public ResultMessage add(BusIllegalFeeModel bif) throws Exception
{
	   ResultMessage result=new ResultMessage();
	   ibfs.add(bif);
	   result.setResult("Y");
	   result.setMessage("增加车辆违章信息成功");
	   return result;  
}
@RequestMapping(value="/modify",method=RequestMethod.POST)
public ResultMessage modify(BusIllegalFeeModel bif) throws Exception
{
	   ResultMessage result=new ResultMessage();
	   ibfs.modify(bif);
	   result.setResult("Y");
	   result.setMessage("修改车辆违章信息成功");
	   return result;
}
@RequestMapping(value="/delete",method=RequestMethod.POST)
public ResultMessage delete(BusIllegalFeeModel bif) throws Exception
{
	   ResultMessage result=new ResultMessage();
	   ibfs.delete(bif);
	   result.setResult("Y");
	   result.setMessage("删除车辆违章信息成功");
	   return result;
} 

//取得指定的车辆违章信息
@RequestMapping(value="/get",method=RequestMethod.GET)
public BusIllegalFeeModel get(int feeno) throws Exception {
	return ibfs.get(feeno);
}
//取得所有车辆违章信息列表，不分页
@RequestMapping(value="/list/all",method=RequestMethod.GET)
public List<BusIllegalFeeModel> getListByAll() throws Exception
{
	   return ibfs.getListByCondition(0,0,0);
}
//取得所有车辆违章信息列表，分页方式
@RequestMapping(value="/list/page",method=RequestMethod.GET)
public List<BusIllegalFeeModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
{
	   return ibfs.getListByAllWithPage(rows, page);
}
//按检索条件取得车辆违章信息列表，有分页,一次性返回分页的所有信息
@RequestMapping(value="/list/condition/page",method=RequestMethod.POST)
public ResultInfo<BusIllegalFeeModel> getListByConditionWithPage(
		   @RequestParam(required=false,defaultValue="0") int busid,
		   @RequestParam(required=false,defaultValue="0") int driverid,
		   @RequestParam(required=false,defaultValue="0") int typeno,
		   @RequestParam(required=false,defaultValue="10") int rows,
		   @RequestParam(required=false,defaultValue="1") int page) throws Exception
{
	   ResultInfo<BusIllegalFeeModel> result=new ResultInfo<BusIllegalFeeModel>();
	   result.setCount(ibfs.getCountByCondition(busid,driverid,typeno));
	   result.setPageCount(ibfs.getPageCountByCondition(busid,driverid,typeno, rows));
	   result.setRows(rows);
	   result.setPage(page);
	   result.setList(ibfs.getListByConditionWithPage(busid,driverid,typeno, rows, page));
	   return result;
}
}
