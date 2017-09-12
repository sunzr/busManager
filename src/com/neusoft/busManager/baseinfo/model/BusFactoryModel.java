package com.neusoft.busManager.baseinfo.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

//车辆厂家model类
@Alias("BusFactory")
public class BusFactoryModel {
   //车辆厂家编号
	private int factoryno;
  //车辆厂家名称
	private String factoryname;
  //车辆厂家职责
	private String factorydesc;
	
	//车辆厂家的附件
	private byte[] photo=null;
	//附件文件名
	private String photoFileName=null;
	//附件文件类型
	private String photoContentType=null; 
  //关联车辆对象
	private List<BusModel> buses;
	
	public int getFactoryno() {
		return factoryno;
	}
	public void setFactoryno(int factoryno) {
		this.factoryno = factoryno;
	}
	public String getFactoryname() {
		return factoryname;
	}
	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}
	public String getFactorydesc() {
		return factorydesc;
	}
	public void setFactorydesc(String factorydesc) {
		this.factorydesc = factorydesc;
	}
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public List<BusModel> getBuses() {
		return buses;
	}
	public void setBuses(List<BusModel> buses) {
		this.buses = buses;
	}
	
}
