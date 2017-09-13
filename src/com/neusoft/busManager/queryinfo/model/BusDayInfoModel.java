package com.neusoft.busManager.queryinfo.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;

//车辆日运行表Model类
@Alias("BusDayInfo")
public class BusDayInfoModel {
	//序号
    private int infono;
	//车辆编号,关联车辆对象
    private BusModel bus;
	//司机编号，关联司机对象
    private BusDriverModel busdriver;
	//日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date infodate;
	//收入金额
    private Double income; 
	//行驶里程
    private int mileage;
	//油耗
    private Double oilwear;
	//其他情况说明
    private String infodesc;
	
	public int getInfono() {
		return infono;
	}
	public void setInfono(int infono) {
		this.infono = infono;
	}
	public BusModel getBus() {
		return bus;
	}
	public void setBus(BusModel bus) {
		this.bus = bus;
	}
	public BusDriverModel getBusdriver() {
		return busdriver;
	}
	public void setBusdriver(BusDriverModel busdriver) {
		this.busdriver = busdriver;
	}
	public Date getInfodate() {
		return infodate;
	}
	public void setInfodate(Date infodate) {
		this.infodate = infodate;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public Double getOilwear() {
		return oilwear;
	}
	public void setOilwear(Double oilwear) {
		this.oilwear = oilwear;
	}
	public String getInfodesc() {
		return infodesc;
	}
	public void setInfodesc(String infodesc) {
		this.infodesc = infodesc;
	}
}
