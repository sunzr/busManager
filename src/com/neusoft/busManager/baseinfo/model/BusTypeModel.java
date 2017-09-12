package com.neusoft.busManager.baseinfo.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

//车辆类型Model类
@Alias("BusType")
public class BusTypeModel {
 //车辆类型编号
	private int typeno;
	//车辆类型名称
	private String typename;
	//车辆类型的附件
	private byte[] photo=null;
	//附件文件名
	private String photoFileName=null;
	//附件文件类型
	private String photoContentType=null; 
	//关联车辆对象
	 private List<BusModel> buses;
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
