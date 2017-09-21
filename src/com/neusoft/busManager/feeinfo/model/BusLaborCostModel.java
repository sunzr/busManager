package com.neusoft.busManager.feeinfo.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;

/**
 * 
 * <p>
 * Title: BusBourcostModel
 * </p>
 * <p>
 * Description: 车辆人工费支出
 * </p>
 * <p>
 * Company:com.sun
 * </p>
 * 
 * @author sun
 * @date 2017年9月19日 下午6:40:23
 */
@Alias("BusLaborCost")
public class BusLaborCostModel {
	
	private int costno;
	private double costAmount;// 支出金额
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date costDate;// 支出日期
	private String costDesc; // 支出说明

	private LaborCostTypeModel costTypeModel;// 人工费支出类型序号
	private BusModel busModel;// 车辆
	private BusDriverModel busDriverModel;// 司机

	public int getCostno() {
		return costno;
	}

	public void setCostno(int costno) {
		this.costno = costno;
	}

	public double getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(double costAmount) {
		this.costAmount = costAmount;
	}

	public Date getCostDate() {
		return costDate;
	}

	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	public String getCostDesc() {
		return costDesc;
	}

	public void setCostDesc(String costDesc) {
		this.costDesc = costDesc;
	}

	public LaborCostTypeModel getCostTypeModel() {
		return costTypeModel;
	}

	public void setCostTypeModel(LaborCostTypeModel costTypeModel) {
		this.costTypeModel = costTypeModel;
	}

	public BusModel getBusModel() {
		return busModel;
	}

	public void setBusModel(BusModel busModel) {
		this.busModel = busModel;
	}

	public BusDriverModel getBusDriverModel() {
		return busDriverModel;
	}

	public void setBusDriverModel(BusDriverModel busDriverModel) {
		this.busDriverModel = busDriverModel;
	}

}
