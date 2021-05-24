package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.StaffListMapper;
import com.gd.sakila.mapper.StaffMapper;
import com.gd.sakila.vo.PageParam;
import com.gd.sakila.vo.Staff;
import com.gd.sakila.vo.StaffView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StaffService {
	@Autowired
	StaffMapper staffMapper; // DI, @AutoWired 없으면 -> NullPoiintException 발생..
	@Autowired
	StaffListMapper staffListMapper;
	
	public Staff login(Staff staff) {
		log.debug("login() param staff :" +staff);
		return staffMapper.selectStaffByLogin(staff); // null or staff 객체
	}
	
	
	
	public Map<String, Object> getStaffList(int currentPage, int rowPerPage, String searchWord){
		
		//
		PageParam page = new PageParam();
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		List<StaffView> staffList = staffListMapper.selectStaffList(page);
		
		//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffList", staffList);
		
		return map;
	}
	
	public Map<String, Object> getStaffOne(int staffId){
		// 상세보기
		Map<String, Object> staffViewOne = staffListMapper.selectStaffOne(staffId);
		
		return staffViewOne;
	}
}
