package com.neusoft.busManager.baseinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.baseinfo.model.BusModel;
import com.neusoft.busManager.baseinfo.service.IBusService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;


//车辆控制器类
@RestController
@RequestMapping("/bus")
public class BusController {
   private IBusService ibs=null;
			@Autowired
			public void setIbs(IBusService ibs) {
				this.ibs = ibs;
			}
		
		@RequestMapping(value="/add",method=RequestMethod.POST)
		   public ResultMessage add(BusModel bm) throws Exception
		   {
			   ResultMessage result=new ResultMessage();
			   ibs.add(bm);
			   result.setResult("Y");
			   result.setMessage("增加车辆成功");
			   return result;  
		   }
		 @RequestMapping(value="/modify",method=RequestMethod.POST)
		   public ResultMessage modify(BusModel bm) throws Exception
		   {
			   ResultMessage result=new ResultMessage();
			   ibs.modify(bm);
			   result.setResult("Y");
			   result.setMessage("修改车辆成功");
			   return result;
		   }
		 @RequestMapping(value="/delete",method=RequestMethod.POST)
		   public ResultMessage delete(BusModel bm) throws Exception
		   {
			   ResultMessage result=new ResultMessage();
			   ibs.delete(bm);
			   result.setResult("Y");
			   result.setMessage("删除车辆成功");
			   return result;
		   } 
		 
		 //取得指定的车辆
		   @RequestMapping(value="/get",method=RequestMethod.GET)
		   public BusModel get(@RequestParam String busid) throws Exception
		   {
			   return ibs.get(busid);
		   }
	   //取得所有车辆列表，不分页
	   @RequestMapping(value="/list/all",method=RequestMethod.GET)
	   public List<BusModel> getListByAll() throws Exception
	   {
		   return ibs.getListByCondition(0,0);
	   }
	 //按检索条件取得车辆列表，无分页
	   @RequestMapping(value="/list/condition",method=RequestMethod.POST)
	   public List<BusModel> getListByCondition(@RequestParam(required=false,defaultValue="0") int typeno,
			   @RequestParam(required=false,defaultValue="0") int factoryno) throws Exception
	   {
		   return ibs.getListByCondition(typeno,factoryno);
	   }
	 //取得所有车辆列表，分页方式
	   @RequestMapping(value="/list/page",method=RequestMethod.GET)
	   public List<BusModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="10") int rows,@RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   return ibs.getListByAllWithPage(rows, page);
	   }
	   //按检索条件取得车辆列表，有分页
	   @RequestMapping(value="/list/condition/page00",method=RequestMethod.POST)
	   public List<BusModel> getListByConditionWithPage00(@RequestParam(required=false,defaultValue="0") int typeno,
			   @RequestParam(required=false,defaultValue="0") int factoryno,
			   @RequestParam(required=false,defaultValue="10") int rows,
			   @RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   return ibs.getListByConditionWithPage(typeno, factoryno,rows, page);
	   }
	 //按检索条件取得车辆列表，有分页,一次性返回分页的所有信息
	   @RequestMapping(value="/list/condition/page",method=RequestMethod.POST)
	   public ResultInfo<BusModel> getListByConditionWithPage(
			   @RequestParam(required=false,defaultValue="0") int typeno,
			   @RequestParam(required=false,defaultValue="0") int factoryno,
			   @RequestParam(required=false,defaultValue="10") int rows,
			   @RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   System.out.println(typeno);
		   ResultInfo<BusModel> result=new ResultInfo<BusModel>();
		   result.setCount(ibs.getCountByCondition(typeno,factoryno));
		   result.setPageCount(ibs.getPageCountByCondition(typeno,factoryno, rows));
		   result.setRows(rows);
		   result.setPage(page);
		   result.setList(ibs.getListByConditionWithPage(typeno,factoryno, rows, page));
		   return result;
	   }
	   
	   //检查指定的楼宇能否被删除
	   @RequestMapping(value="/checkcandelete",method=RequestMethod.GET)
	   public ResultMessage checkCanDelete(@RequestParam String busid) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   return result;
	   }
	    
}
