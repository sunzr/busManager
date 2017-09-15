package com.neusoft.busManager.repairinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 车辆维修明细表
 * @author niududu
 *
 */
@Alias("BusRepairDetail")
public class BusRepairInfoDetailModel {
	//维修明细序号
	private int detailNo;
	//维修项目名称
	private String item;
	//维修项目个数
	private double itemQTY;
	//维修项目单价
	private double itemUnitPrice;
	//维修项目说明
	private String itemDesc;
	//维修序号（一个维修明细表对应一个维修表：一对一）
	private BusRepairInfoModel reapirInfo;
	public int getDetailNo() {
		return detailNo;
	}
	public void setDetailNo(int detailNo) {
		this.detailNo = detailNo;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getItemQTY() {
		return itemQTY;
	}
	public void setItemQTY(double itemQTY) {
		this.itemQTY = itemQTY;
	}
	public double getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public BusRepairInfoModel getReapirInfo() {
		return reapirInfo;
	}
	public void setReapirInfo(BusRepairInfoModel reapirInfo) {
		this.reapirInfo = reapirInfo;
	}
	
}
