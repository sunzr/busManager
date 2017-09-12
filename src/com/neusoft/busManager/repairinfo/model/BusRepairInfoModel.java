package com.neusoft.busManager.repairinfo.model;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neusoft.busManager.baseinfo.model.BusDriverModel;
import com.neusoft.busManager.baseinfo.model.BusModel;

/**
 * 车辆维修表
 * @author niududu
 *
 */
@Alias("BusRepair")
public class BusRepairInfoModel {
	//维修序号
	private int reapirNo;
	//维修日期
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")  
	private Date repairDate;
	//维修说明
	private String repairDesc;
	/*外键*/
	//维修类型编号(一个车辆维修表可以对应多个维修类型：一对多 )
	private List<RepairTypeModel> reapirtypeId;
	//维修单位编号（一个车辆维修表可以对应多个维修单位：一对多）
	private List<RepairProviderModel> providerNo;
	//车辆编号（一对一）
	private BusModel busId;
	//司机编号（一对一）
	private  BusDriverModel driveId;
	public int getReapirNo() {
		return reapirNo;
	}
	public void setReapirNo(int reapirNo) {
		this.reapirNo = reapirNo;
	}
	public Date getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	public String getRepairDesc() {
		return repairDesc;
	}
	public void setRepairDesc(String repairDesc) {
		this.repairDesc = repairDesc;
	}
	public List<RepairTypeModel> getReapirtypeId() {
		return reapirtypeId;
	}
	public void setReapirtypeId(List<RepairTypeModel> reapirtypeId) {
		this.reapirtypeId = reapirtypeId;
	}
	public List<RepairProviderModel> getProviderNo() {
		return providerNo;
	}
	public void setProviderNo(List<RepairProviderModel> providerNo) {
		this.providerNo = providerNo;
	}
	public BusModel getBusId() {
		return busId;
	}
	public void setBusId(BusModel busId) {
		this.busId = busId;
	}
	public BusDriverModel getDriveId() {
		return driveId;
	}
	public void setDriveId(BusDriverModel driveId) {
		this.driveId = driveId;
	}
	
	
}
