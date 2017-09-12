package com.neusoft.busManager.query.model;

import org.apache.ibatis.type.Alias;

@Alias("IllegalType")
public class IllegalTypeModel {
	
	private int typeno;
	private String typename;
	private int payscore;
	private double payfee;
	
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
	
	
}
