package com.neusoft.busManager.queryinfo.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("IllegalType")
public class IllegalTypeModel {
	
	private int typeno;
	private String typename;
	private int payscore;
	private double payfee;
	//关联车辆违章信息
		private List<BusIllegalFeeModel> busillegalfee; 
	
	public int getTypeno() {
		return typeno;
	}
	public void setTypeno(int typeno) {
		this.typeno = typeno;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public int getPayscore() {
		return payscore;
	}
	public void setPayscore(int payscore) {
		this.payscore = payscore;
	}
	public double getPayfee() {
		return payfee;
	}
	public void setPayfee(double payfee) {
		this.payfee = payfee;
	}
	public List<BusIllegalFeeModel> getBusillegalfee() {
		return busillegalfee;
	}
	public void setBusillegalfee(List<BusIllegalFeeModel> busillegalfee) {
		this.busillegalfee = busillegalfee;
	}
	
	
}
