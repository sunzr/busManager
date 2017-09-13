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


@RestController
@RequestMapping("/busoil")
public class BusOilController {
	  
	   @Autowired
	   private IBusOilInfoService busOilInfoService;
	
	   @RequestMapping(value="/list/condition/page",method=RequestMethod.POST)
	   public ResultInfo<BusOilInfoModel> getListByConditionWithPage(
			   @RequestParam(required=false,defaultValue="0") int busNo,
			   @RequestParam(required=false,defaultValue="0") int busDriverNo,
			   @RequestParam(required=false,defaultValue="10") int rows,
			   @RequestParam(required=false,defaultValue="1") int page) throws Exception
	   {
		   ResultInfo<BusOilInfoModel> result=new ResultInfo<BusOilInfoModel>();
		   result.setCount(busOilInfoService.selectCountByCondition(busNo, busDriverNo));
		   result.setPageCount(busOilInfoService.selectPageCountByCondition(busNo, busDriverNo,rows));
		   result.setRows(rows);
		   result.setPage(page);
		   result.setList(busOilInfoService.selectListByConditionWithPage(busNo, busDriverNo, rows, page));
		   return result;
	   }
	   
		@RequestMapping(value="/add",method=RequestMethod.POST)
	   public ResultMessage add(BusOilInfoModel busoil) throws Exception
	   {
		   ResultMessage result=new ResultMessage();
		   busOilInfoService.insert(busoil);
		   result.setResult("Y");
		   result.setMessage("增加加油记录成功");
		   return result;
		   
		  
	   }
   
}
