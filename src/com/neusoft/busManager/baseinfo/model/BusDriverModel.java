package com.neusoft.busManager.baseinfo.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.busManager.queryinfo.model.BusDayInfoModel;
import com.neusoft.busManager.queryinfo.model.BusIllegalFeeModel;

//司机信息Model类
@Alias("BusDriver")
public class BusDriverModel {
 //司机编号
	private int driverid;
 //司机姓名
	private String dname;
 //司机性别
	private String sex;
 //司机年龄
	private int age;
 //司机出生日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date birthday;
 //司机身份证号
	private String dcard;
 //司机驾照编号
	private String dcode;
 //司机入职日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date joindate;
 //司机离职日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date leavedate;
	
//司机的当前状态
	private String dstatus;
	
//司机信息的附件
   private byte[] photo=null;
	//附件文件名
	private String photoFileName=null;
	//附件文件类型
	private String photoContentType=null; 
	
	//关联车辆日运行信息对象
		private List<BusDayInfoModel> busdayinfo;
    //关联车辆违章信息
		private List<BusIllegalFeeModel> busillegalfee;
	public int getDriverid() {
			return driverid;
		}
		public void setDriverid(int driverid) {
			this.driverid = driverid;
		}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDcard() {
		return dcard;
	}
	public void setDcard(String dcard) {
		this.dcard = dcard;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public Date getLeavedate() {
		return leavedate;
	}
	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}
	public String getDstatus() {
		return dstatus;
	}
	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
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
	public List<BusDayInfoModel> getBusdayinfo() {
		return busdayinfo;
	}
	public void setBusdayinfo(List<BusDayInfoModel> busdayinfo) {
		this.busdayinfo = busdayinfo;
	}
	public List<BusIllegalFeeModel> getBusillegalfee() {
		return busillegalfee;
	}
	public void setBusillegalfee(List<BusIllegalFeeModel> busillegalfee) {
		this.busillegalfee = busillegalfee;
	}
	

}
