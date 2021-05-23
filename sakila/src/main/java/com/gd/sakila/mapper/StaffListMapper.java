package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.StaffList;

@Mapper
public interface StaffListMapper {
	List<StaffList> selectStaffList(PageParam page);
	Map<String, Object> selectStaffOne(int ID);
}
