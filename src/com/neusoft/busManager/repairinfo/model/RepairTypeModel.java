package com.neusoft.busManager.repairinfo.model;

import org.apache.ibatis.type.Alias;

/**
 * 维修类型
 * @author niududu
 *
 */
@Alias("RepairType")
public class RepairTypeModel {
	//类型编号
	private int typeNo;
	//类型名称
	private String typeName;
	//类型详情
	private String typeDesc;
	public int getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
}
