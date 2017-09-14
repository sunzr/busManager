package com.neusoft.busManager.queryinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.queryinfo.model.BusOilInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusOilInfoService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//楼宇控制器类
@RestController
@RequestMapping(value="/busoil")
public class BusOilController {


	@Autowired
	private IBusOilInfoService busOilService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
   public ResultMessage add(BusOilInfoModel oilModel) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   busOilService.insert(oilModel);
	   result.setResult("Y");
	   result.setMessage("增加加油信息成功");
	   return result;
	   
	  
   }
   
   @RequestMapping(value="/modify",method=RequestMethod.POST)
   public ResultMessage modify(BusOilInfoModel oilModel) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   busOilService.update(oilModel);
	   result.setResult("Y");
	   result.setMessage("修改成功");
	   return result;
	  
   }
   
   @RequestMapping(value="/delete",method=RequestMethod.POST)
   public ResultMessage delete(BusOilInfoModel bm) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   busOilService.delete(bm);
	   result.setResult("Y");
	   result.setMessage("删除加油信息表成功");
	   return result;
   }
   //取得指定的楼宇
   @RequestMapping(value="/get",method=RequestMethod.GET)
   public BusOilInfoModel get(@RequestParam int infono) throws Exception
   {
	   return busOilService.select(infono);
   }


   
   //按检索条件取得楼宇列表，有分页,一次性返回分页的所有信息
   @RequestMapping(value="/list/condition/page",method=RequestMethod.POST)
   public ResultInfo<BusOilInfoModel> getListByConditionWithPage(
		   @RequestParam(required=false,defaultValue="0") int busid,
		   @RequestParam(required=false,defaultValue="0") int driverid,
		   @RequestParam(required=false,defaultValue="10") int rows,
		   @RequestParam(required=false,defaultValue="1") int page) throws Exception
   {
	   ResultInfo<BusOilInfoModel> result=new ResultInfo<BusOilInfoModel>();
	   result.setCount(busOilService.selectCountByCondition(busid, driverid));
	   result.setPageCount(busOilService.selectPageCountByCondition(busid, driverid,rows));
	   result.setRows(rows);
	   result.setPage(page);
	   result.setList(busOilService.selectListByConditionWithPage(busid, driverid, rows, page));
	   return result;
   }
   
   //检查指定的楼宇能否被删除
   @RequestMapping(value="/checkcandelete",method=RequestMethod.GET)
   public ResultMessage checkCanDelete(@RequestParam int buildingNo) throws Exception
   {
	   ResultMessage result=new ResultMessage();
	   
	   return result;
   }
   
   
}
