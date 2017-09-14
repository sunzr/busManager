package com.neusoft.busManager.repairinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.repairinfo.model.BusRepairInfoDetailModel;
import com.neusoft.busManager.repairinfo.service.IBusRepairInfoDetailService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

//车辆维修明细表控制器
@RestController
@RequestMapping(value="/busrepairinfodetail")
public class BusRepairInfoDetailController {
	@Autowired
	private IBusRepairInfoDetailService ibrids;
	@RequestMapping(value="/add",method={RequestMethod.POST})
	public ResultMessage add(BusRepairInfoDetailModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("增加车辆维修明细表失败");
		ibrids.add(model);
		result.setResult("Y");
		result.setMessage("增加车辆维修明细表成功");
		return result;
	}

	@RequestMapping(value="/modify",method={RequestMethod.POST})
	public ResultMessage modify(BusRepairInfoDetailModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("修改车辆维修明细表失败");
		ibrids.modify(model);
		result.setResult("Y");
		result.setMessage("修改车辆维修明细表成功");
		return result;
	}
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public ResultMessage delete(BusRepairInfoDetailModel model) throws Exception{
		ResultMessage result=new ResultMessage();
		result.setMessage("删除车辆维修明细表失败");
		ibrids.delete(model);
		result.setResult("Y");
		result.setMessage("删除车辆维修明细表成功");
		return result;
	}
	//取得指定的车辆维修明细表
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public BusRepairInfoDetailModel get(@RequestParam int detailNo) throws Exception{
		return ibrids.get(detailNo);
	}
	//取得所有的车辆维修明细表列表
	@RequestMapping(value="/list/all",method=RequestMethod.GET,produces="application/json")
	public List<BusRepairInfoDetailModel> getListByAll() throws Exception{
		return ibrids.getListByAll();
	}
	//以分页的方式取得所有的车辆维修明细表列表
	@RequestMapping(value="/list/page",method=RequestMethod.GET,produces="application/json")
	public ResultInfo<BusRepairInfoDetailModel> getListByAllWithPage(@RequestParam(required=false,defaultValue="5")int rows,@RequestParam(required=false,defaultValue="1")int page) throws Exception{
		int count=ibrids.getCountByAll();
		int pageCount=ibrids.getPageCountByAll(rows);
		List<BusRepairInfoDetailModel> list=ibrids.getListByAllWithPage(rows, page);
		ResultInfo<BusRepairInfoDetailModel> result=new ResultInfo<BusRepairInfoDetailModel>();
		result.setCount(count);
		result.setPage(page);
		result.setPageCount(pageCount);
		result.setRows(rows);
		result.setList(list);
		return result;
	} 
	//重名
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam int detailNo) throws Exception{
		if(ibrids.get(detailNo)!=null){
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
