package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.StaffView;

@Mapper
public interface StaffListMapper {
	List<StaffView> selectStaffList(PageParam page);
	Map<String, Object> selectStaffOne(int ID);
}
