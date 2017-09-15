package com.neusoft.busManager.queryinfo.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;

//车辆违章的Model类
@Alias("BusIllegalFee")
public class BusIllegalFeeModel {
	//违章序号
	private int feeno;
	//车辆编号，关联车辆对象
	private BusModel bus;
	//司机编号，关联司机对象
	private BusDriverModel busdriver;
	//违章类型编号，关联违章类型对象
	private IllegalTypeModel illegaltype;
	//违章日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date illeagaldate;
	//缴费日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date paydate;
	//罚款金额
	private int payfee;
	//扣分分数
	private int paysocore;
	//违章说明
	private String feedesc;
	public int getFeeno() {
		return feeno;
	}
	public void setFeeno(int feeno) {
		this.feeno = feeno;
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
	public IllegalTypeModel getIllegaltype() {
		return illegaltype;
	}
	public void setIllegaltype(IllegalTypeModel illegaltype) {
		this.illegaltype = illegaltype;
	}
	public Date getIlleagaldate() {
		return illeagaldate;
	}
	public void setIlleagaldate(Date illeagaldate) {
		this.illeagaldate = illeagaldate;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public int getPayfee() {
		return payfee;
	}
	public void setPayfee(int payfee) {
		this.payfee = payfee;
	}
	public int getPaysocore() {
		return paysocore;
	}
	public void setPaysocore(int paysocore) {
		this.paysocore = paysocore;
	}
	public String getFeedesc() {
		return feedesc;
	}
	public void setFeedesc(String feedesc) {
		this.feedesc = feedesc;
	}
	


}
