package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.StaffService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired
	StaffService staffService;
	
	@GetMapping("/getStaffList")
	public String getStaffList(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage, 
											@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage,
											@RequestParam(value="searchWord", required=false) String searchWord) {
		log.debug("currentPage : "+currentPage);
		log.debug("rowPerPage : "+rowPerPage);
		log.debug("searchWord : "+searchWord);
		
		Map<String, Object> map = staffService.getStaffList(currentPage, rowPerPage, searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("staffList",map.get("staffList"));
			
		return "getStaffList";
	}
	
	
	@GetMapping("/getStaffOne")
	public String getStaffOne(Model model, @RequestParam(value = "ID", required = true) int ID) {
		log.debug("ID : "+ ID);
		Map<String, Object> staffMap = staffService.getStaffOne(ID);
		log.debug("staffMap :"+staffMap);
		model.addAttribute("staffMap", staffMap );
		
		return "getStaffOne";
	}

}