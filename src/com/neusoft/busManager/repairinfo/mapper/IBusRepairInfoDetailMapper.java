package com.neusoft.busManager.repairinfo.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.repairinfo.model.BusRepairInfoDetailModel;

//方法名得和相应的 mapper.xml 中的 id 保持一致
public interface IBusRepairInfoDetailMapper {
	//增加维修车辆明细表
	public void insert(BusRepairInfoDetailModel model) throws Exception;
	//修改维修车辆明细表
	public void update(BusRepairInfoDetailModel model) throws Exception;
	//删除维修车辆明细表
	public void delete(BusRepairInfoDetailModel model) throws Exception;
	//查找指定的维修车辆明细表
	public BusRepairInfoDetailModel select(int providerNo) throws Exception;
	//按车辆维修表查找指定的维修车辆明细表
	public BusRepairInfoDetailModel selectByRepairInfo(int reapirNo) throws Exception;
	//查找所有的维修车辆明细表列表
	public List<BusRepairInfoDetailModel> selectListByAll() throws Exception;
	//以分页的形式查找所有的维修车辆明细表列表
	public List<BusRepairInfoDetailModel> selectListByAllWithPage(RowBounds rb)throws Exception; 
	//查找所有的维修车辆明细表的个数
	public int selectCountByAll() throws Exception;
}
