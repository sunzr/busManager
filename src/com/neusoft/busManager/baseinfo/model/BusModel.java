package com.neusoft.busManager.baseinfo.model;

import org.apache.ibatis.type.Alias;

//车辆Model类
@Alias("Bus")
public class BusModel {
	//车辆编号
	private String busid;
	//车牌号
	private String buscardid;
	//车架号
	private String vinno;
	//关联的车辆类型对象
	private BusTypeModel bustype;
	//关联的车辆厂家对象
	private BusFactoryModel busfactory;
	//排量
	private Double output;
	//座位数
	private int seating;
	//总行驶里程
	private int totalmileage;
	//车辆尺寸:长宽高
	private String bussize;
	//标称油耗
	private Double oilwear;
	//载重量
	private Double wload;
	
	public String getBusid() {
		return busid;
	}
	public void setBusid(String busid) {
		this.busid = busid;
	}
	public String getBuscardid() {
		return buscardid;
	}
	public void setBuscardid(String buscardid) {
		this.buscardid = buscardid;
	}
	public String getVinno() {
		return vinno;
	}
	public void setVinno(String vinno) {
		this.vinno = vinno;
	}
	public BusTypeModel getBustype() {
		return bustype;
	}
	public void setBustype(BusTypeModel bustype) {
		this.bustype = bustype;
	}
	public BusFactoryModel getBusfactory() {
		return busfactory;
	}
	public void setBusfactory(BusFactoryModel busfactory) {
		this.busfactory = busfactory;
	}
	public Double getOutput() {
		return output;
	}
	public void setOutput(Double output) {
		this.output = output;
	}
	public int getSeating() {
		return seating;
	}
	public void setSeating(int seating) {
		this.seating = seating;
	}
	public int getTotalmileage() {
		return totalmileage;
	}
	public void setTotalmileage(int totalmileage) {
		this.totalmileage = totalmileage;
	}
	public String getBussize() {
		return bussize;
	}
	public void setBussize(String bussize) {
		this.bussize = bussize;
	}
	public Double getOilwear() {
		return oilwear;
	}
	public void setOilwear(Double oilwear) {
		this.oilwear = oilwear;
	}
	public Double getWload() {
		return wload;
	}
	public void setWload(Double wload) {
		this.wload = wload;
	}

}
