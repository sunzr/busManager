package com.neusoft.busManager.feeinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.busManager.feeinfo.model.BusLaborCostModel;
import com.neusoft.busManager.feeinfo.service.IBusLaborCostService;
import com.neusoft.busManager.util.ResultInfo;
import com.neusoft.busManager.util.ResultMessage;

@RequestMapping("/buslaborcost")
@RestController
public class BusLaborCostController {

	@Autowired
	private IBusLaborCostService payTypeService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultMessage add(BusLaborCostModel bpfm) throws Exception {
		ResultMessage result = new ResultMessage();
		payTypeService.add(bpfm);
		result.setResult("Y");
		result.setMessage("增加车辆人工费支出成功");
		return result;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResultMessage modify(BusLaborCostModel bpfm) throws Exception {
		ResultMessage result = new ResultMessage();
		System.out.println(bpfm.getCostno());
		payTypeService.modify(bpfm);
		result.setResult("Y");
		result.setMessage("修改车辆人工费支出成功");
		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResultMessage delete(BusLaborCostModel bpfm) throws Exception {
		ResultMessage result = new ResultMessage();
		payTypeService.delete(bpfm);
		result.setResult("Y");
		result.setMessage("删除车辆人工费支出成功");
		return result;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public BusLaborCostModel get(@RequestParam int costno) throws Exception {
		return payTypeService.get(costno);
	}

	// 取得所有车辆人工费支出列表，无分页
	@RequestMapping(value = "/list/all", method = RequestMethod.GET)
	public List<BusLaborCostModel> getListByAll() throws Exception {
		return payTypeService.getListByAll();

	}

	// 取得所有车辆人工费支出列表，分页模式
	@RequestMapping(value = "/list/page", method = RequestMethod.POST)
	public ResultInfo<BusLaborCostModel> getListByAllWithPage(
			@RequestParam(required = false, defaultValue = "10") int rows,
			@RequestParam(required = false, defaultValue = "1") int page) throws Exception {
		ResultInfo<BusLaborCostModel> result = new ResultInfo<BusLaborCostModel>();
		result.setCount(payTypeService.getCountByAll());
		result.setPageCount(payTypeService.getPageCountByAll(rows));
		result.setPage(page);
		result.setRows(rows);
		result.setList(payTypeService.getListByAllWithPage(rows, page));
		return result;

	}

	// 取得所有车辆人工费支出列表，分页模式,一次性返回分页的所有信息
	@RequestMapping(value = "/list/condition/page", method = RequestMethod.POST)
	public ResultInfo<BusLaborCostModel> getListByConditionWithPage(
			@RequestParam(required = false, defaultValue = "0") int typeno,
			@RequestParam(required = false, defaultValue = "0") int busid,
			@RequestParam(required = false, defaultValue = "0") int driverid,
			@RequestParam(required = false, defaultValue = "10") int rows,
			@RequestParam(required = false, defaultValue = "1") int page) throws Exception {
		ResultInfo<BusLaborCostModel> result = new ResultInfo<BusLaborCostModel>();
		result.setCount(payTypeService.getCountByCondition(typeno, busid, driverid));
		result.setPageCount(payTypeService.getPageCountByCondition(typeno, busid, driverid, rows));
		result.setRows(rows);
		result.setPage(page);
		result.setList(payTypeService.getListByConditionWithPage(typeno, busid, driverid, rows, page));
		return result;
	}

	// 检查指定的车辆人工费支出能否被删除
	@RequestMapping(value = "/checkcandelete", method = RequestMethod.GET, produces = "application/json")
	public ResultMessage checkCanDelete(@RequestParam int costno) throws Exception {
		ResultMessage result = new ResultMessage();
		if (payTypeService.checkCanDelete(costno)) {
			result.setResult("Y");
			result.setMessage("此车辆人工费支出可以被删除!");
		} else {
			result.setResult("N");
			result.setMessage("此车辆人工费支出不能被删除!");
		}
		return result;
	}

	// 检查指定的车辆人工费支出名称是否存在
	@RequestMapping(value = "/checkNameExist", method = RequestMethod.GET, produces = "application/json")
	public boolean checkNameExist(@RequestParam String typename) throws Exception {
		return !payTypeService.checkNameExist(typename);

	}

}
