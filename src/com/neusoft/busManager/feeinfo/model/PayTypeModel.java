package com.neusoft.busManager.feeinfo.model;

import org.apache.ibatis.type.Alias;

@Alias("PayType")
public class PayTypeModel {
	private int typeno;
	private String typename;
	private double payfee;// 缴费金额标准
	private String payto; // 缴费接收单位

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

	public double getPayfee() {
		return payfee;
	}

	public void setPayfee(double payfee) {
		this.payfee = payfee;
	}

	public String getPayto() {
		return payto;
	}

	public void setPayto(String payto) {
		this.payto = payto;
	}

}
