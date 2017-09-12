package com.neusoft.busManager.query.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.neusoft.busManager.query.model.BusOilInfoModel;



public interface IBusOilInfoMapper {
	//增加楼宇
	public void insert(BusOilInfoModel boi) throws Exception;
	//修改楼宇
	public void update(BusOilInfoModel boi) throws Exception;
	//删除楼宇
	public void delete(BusOilInfoModel boi) throws Exception;
	//取得指定的楼宇
	public BusOilInfoModel select(int infono) throws Exception;
	//取得所有楼宇列表
	public List<BusOilInfoModel> selectListByAll() throws Exception;
	//按小区取得楼宇列表
	public List<BusOilInfoModel> selectListByArea(int areaNo) throws Exception;
	//按建筑取得楼宇列表
	public List<BusOilInfoModel> selectListByBuildingType(int areaNo) throws Exception;
	//按检索条件取得楼宇列表
	public List<BusOilInfoModel> selectListByCondition(@Param("areaNo") int areaNo,@Param("buildingTypeNo") int buildingTypeNo,@Param("code") String code) throws Exception;
	//分页方式取得楼宇列表
	public List<BusOilInfoModel> selectListByAllWithPage(RowBounds rb) throws Exception;
	//按检索条件取得楼宇列表,分页方式
	public List<BusOilInfoModel> selectListByConditionWithPage(@Param("areaNo") int areaNo,@Param("buildingTypeNo") int buildingTypeNo,@Param("code") String code,RowBounds rb) throws Exception;
	//取得建筑楼宇的个数
	public int selectCount() throws Exception;
	//按检索条件取得建筑楼宇的个数
	public int selectCountByCondition(@Param("areaNo") int areaNo,@Param("buildingTypeNo") int buildingTypeNo,@Param("code") String code) throws Exception;

}	
