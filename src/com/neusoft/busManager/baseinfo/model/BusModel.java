package com.neusoft.busManager.baseinfo.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;

//车辆Model类
@Alias("Bus")
public class BusModel {
	//车辆编号
	private int busid;
	//车辆名称
	private String busname;
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
	
	//关联车辆日运行信息对象
	private List<BusDayInfoModel> busdayinfo;
	public int getBusid() {
		return busid;
	}
	public void setBusid(int busid) {
		this.busid = busid;
	}
	public String getBusname() {
		return busname;
	}
	public void setBusname(String busname) {
		this.busname = busname;
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
	public List<BusDayInfoModel> getBusdayinfo() {
		return busdayinfo;
	}
	public void setBusdayinfo(List<BusDayInfoModel> busdayinfo) {
		this.busdayinfo = busdayinfo;
	}

}
