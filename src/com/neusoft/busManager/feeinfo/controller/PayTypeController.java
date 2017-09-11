package com.neusoft.busManager.feeinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.feeinfo.model.PayTypeModel;
import com.neusoft.busManager.feeinfo.service.IPayTypeService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

@RequestMapping("/paytype")
@RestController
public class PayTypeController {

	@Autowired
	private IPayTypeService payTypeService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultMessage add(PayTypeModel ptm) throws Exception {
		ResultMessage result = new ResultMessage();
		PayTypeModel PayTypeModel = payTypeService.get(ptm.getTypeno());
		payTypeService.add(ptm);
		result.setResult("Y");
		result.setMessage("增加缴费类型成功");
		return result;

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResultMessage modify(PayTypeModel ptm) throws Exception {
		ResultMessage result = new ResultMessage();
		payTypeService.modify(ptm);
		result.setResult("Y");
		result.setMessage("修改缴费类型成功");
		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResultMessage delete(PayTypeModel ptm) throws Exception {
		ResultMessage result = new ResultMessage();
		payTypeService.delete(ptm);
		result.setResult("Y");
		result.setMessage("删除缴费类型成功");
		return result;

	}

	 @RequestMapping(value="/get",method=RequestMethod.GET)
	 public PayTypeModel get(@RequestParam int typeno) throws Exception
	  {
		   return payTypeService.get(typeno);
	  }
	// 取得所有缴费类型列表，无分页
	@RequestMapping(value = "/list/all", method = RequestMethod.GET)
	public List<PayTypeModel> getListByAll() throws Exception {
		return payTypeService.getListByAll();

	}

	// 取得所有缴费类型列表，分页模式
	@RequestMapping(value = "/list/page", method = RequestMethod.GET)
	public ResultInfo<PayTypeModel> getListByAllWithPage(@RequestParam(required = false, defaultValue = "10") int rows,
			@RequestParam(required = false, defaultValue = "1") int page) throws Exception {
		ResultInfo<PayTypeModel> result = new ResultInfo<PayTypeModel>();
		result.setCount(payTypeService.getCountByAll());
		result.setPageCount(payTypeService.getPageCountByAll(rows));
		result.setPage(page);
		result.setRows(rows);
		result.setList(payTypeService.getListByAllWithPage(rows, page));
		return result;

	}

 	
	// 检查指定的缴费类型能否被删除
	@RequestMapping(value = "/checkcandelete", method = RequestMethod.GET,produces="application/json")
	public ResultMessage checkCanDelete(@RequestParam int typeno) throws Exception {
		ResultMessage result = new ResultMessage();
		if (payTypeService.checkCanDelete(typeno)) {
			result.setResult("Y");
			result.setMessage("此缴费类型可以被删除!");
		} else {
			result.setResult("N");
			result.setMessage("此缴费类型不能被删除!");
		}
		return result;
	}
 
	// 检查指定的缴费类型名称是否存在
	@RequestMapping(value="/checkNameExist",method=RequestMethod.GET,produces="application/json")
	public boolean checkNameExist(@RequestParam String typename) throws Exception{
		return !payTypeService.checkNameExist(typename);  
		
	}
}
