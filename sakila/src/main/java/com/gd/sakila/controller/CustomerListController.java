package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CustomerService;
import com.gd.sakila.vo.CustomerList;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class CustomerListController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getCustomerList")
	public String getCustomerList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
									@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
									@RequestParam(value = "searchWord", required = false) String searchWord) {
		log.debug("CustomerListController에서 CustomerListController -> currentPage: "+currentPage);
		log.debug("CustomerListController에서 CustomerListController -> rowPerPage: "+rowPerPage);
		log.debug("CustomerListController에서 CustomerListController -> searchWord: "+searchWord);
		
		int beginRow = (currentPage-1) * rowPerPage;
		log.debug("CustomerListController에서 CustomerListController -> beginRow: "+beginRow);

		Map<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", searchWord);
		map.put("beginRow", beginRow);
		
		List<CustomerList> list = customerService.getCustomerList(map);
		log.debug("CustomerListController에서 CustomerListController -> list: "+list);
		
		model.addAttribute("list", list);
		model.addAttribute("beginRow", beginRow);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("currentPage", currentPage);
		
		return "getCustomerList";
	}
}
