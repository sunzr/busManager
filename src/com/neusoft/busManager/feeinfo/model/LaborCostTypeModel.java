package com.neusoft.busManager.feeinfo.model;

import org.apache.ibatis.type.Alias;
/**
 * 人工费类型
 * @author sunzr
 *
 */
@Alias("LaborCostType")
public class LaborCostTypeModel {
	private int typeno;
	private String typename;

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

}
