package com.neusoft.busManager.feeinfo.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;

/**
 * 车辆缴费
 * 
 * @author sunzr
 *
 */
@Alias("BusPayFee")
public class BusPayFeeModel {
	private int payno = 0;

	private PayTypeModel payTypeModel = null;// 缴费类型序号
	private BusModel busModel = null;// 车辆编号
	private BusDriverModel busDriverModel = null;// 司机编号

	private String payTo = null;// 收费单位名称
	private double payAmount = 0;// 缴费金额
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date payDate;// 缴费日期
	private String payDesc = null;// 缴费说明

	public int getPayno() {
		return payno;
	}

	public void setPayno(int payno) {
		this.payno = payno;
	}

	public PayTypeModel getPayTypeModel() {
		return payTypeModel;
	}

	public void setPayTypeModel(PayTypeModel payTypeModel) {
		this.payTypeModel = payTypeModel;
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

	public String getPayTo() {
		return payTo;
	}

	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}

}
