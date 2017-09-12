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
	private int DetailNo;
	//维修项目名称
	private String Item;
	//维修项目个数
	private double ItemQTY;
	//维修项目单价
	private double itemUnitPrice;
	//维修项目说明
	private String ItemDesc;
	//维修序号（一个维修明细表对应一个维修表：一对一）
	private BusRepairInfoModel reapirNo;
	
	
	public BusRepairInfoModel getReapirNo() {
		return reapirNo;
	}
	public void setReapirNo(BusRepairInfoModel reapirNo) {
		this.reapirNo = reapirNo;
	}
	public int getDetailNo() {
		return DetailNo;
	}
	public void setDetailNo(int detailNo) {
		DetailNo = detailNo;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public double getItemQTY() {
		return ItemQTY;
	}
	public void setItemQTY(double itemQTY) {
		ItemQTY = itemQTY;
	}
	public double getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
	public String getItemDesc() {
		return ItemDesc;
	}
	public void setItemDesc(String itemDesc) {
		ItemDesc = itemDesc;
	}
	
}
