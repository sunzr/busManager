package com.neusoft.busManager.repairinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 维修单位表
 * @author niududu
 *
 */
@Alias("RepairProvider")
public class RepairProviderModel {
	//维修单位序号
	private int providerNo;
	//维修单位名称
	private String providerName;
	//联系人
	private String providerContact;
	//单位地址
	private String providerAddress;
	//手机
	private String providerMobile;
	//固定电话
	private String providerTel;
	//单位介绍
	private String providerDesc;

	public int getProviderNo() {
		return providerNo;
	}
	public void setProviderNo(int providerNo) {
		this.providerNo = providerNo;
	}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderContact() {
		return providerContact;
	}
	public void setProviderContact(String providerContact) {
		this.providerContact = providerContact;
	}
	public String getProviderAddress() {
		return providerAddress;
	}
	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}
	public String getProviderMobile() {
		return providerMobile;
	}
	public void setProviderMobile(String providerMobile) {
		this.providerMobile = providerMobile;
	}
	public String getProviderTel() {
		return providerTel;
	}
	public void setProviderTel(String providerTel) {
		this.providerTel = providerTel;
	}
	public String getProviderDesc() {
		return providerDesc;
	}
	public void setProviderDesc(String providerDesc) {
		this.providerDesc = providerDesc;
	}
	
}
