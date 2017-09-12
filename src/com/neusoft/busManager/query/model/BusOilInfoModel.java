package com.neusoft.busManager.query.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;

@Alias("BusOilInfo")
public class BusOilInfoModel {
	private int infono;
	private BusModel bus;
	private BusDriverModel driver;
	private double oilvolume;
	private double oilfee;
	private Date infodate;
	private int busmile;
	
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
	public BusDriverModel getDriver() {
		return driver;
	}
	public void setDriver(BusDriverModel driver) {
		this.driver = driver;
	}
	public double getOilvolume() {
		return oilvolume;
	}
	public void setOilvolume(double oilvolume) {
		this.oilvolume = oilvolume;
	}
	public double getOilfee() {
		return oilfee;
	}
	public void setOilfee(double oilfee) {
		this.oilfee = oilfee;
	}
	public Date getInfodate() {
		return infodate;
	}
	public void setInfodate(Date infodate) {
		this.infodate = infodate;
	}
	public int getBusmile() {
		return busmile;
	}
	public void setBusmile(int busmile) {
		this.busmile = busmile;
	}
	
	
}
