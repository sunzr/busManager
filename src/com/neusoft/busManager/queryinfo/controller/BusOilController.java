package com.neusoft.busManager.queryinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.queryinfo.model.BusOilInfoModel;
import com.neusoft.busManager.queryinfo.service.IBusOilInfoService;
import com.neusoft.busManager.util.ResultInfo;


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
}
