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
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getCustomerList")
	public String getCustomerList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
									@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
									@RequestParam(value = "searchWord", required = false) String searchWord ) {
		log.debug("CustomerListController에서 CustomerListController -> currentPage: "+currentPage);
		log.debug("CustomerListController에서 CustomerListController -> rowPerPage: "+rowPerPage);
		log.debug("CustomerListController에서 CustomerListController -> searchWord: "+searchWord);
		
		// beginRow
		int beginRow = (currentPage-1) * rowPerPage;
		log.debug("CustomerListController에서 CustomerListController -> beginRow: "+beginRow);

		// customerTotal
		int customerTotal = customerService.getCustomerTotal(searchWord);
		log.debug("CustomerListController에서 CustomerListController -> customerTotal: "+customerTotal);
		
		// lastPage
		int lastPage = customerTotal / rowPerPage;
		if(customerTotal / rowPerPage != 0) { //나누어 떨어지지 않으면
			lastPage++; 
		}
		log.debug("CustomerListController에서 CustomerListController -> lastPage: "+lastPage);
		
		// map에 파라미터 데이터를 넣어줌. (
		Map<String, Object> map = new HashMap<>();
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", searchWord);
		map.put("beginRow", beginRow);

		
		// 블랙리스트 
		List<Map<String, Object>> blackCustomerList = customerService.getBlackCustomerList();
		log.debug("CustomerListController에서 CustomerListController -> blackCustomerList: "+blackCustomerList);
		
		
		// customerList
		List<CustomerList> customerList = customerService.getCustomerList(map);
		log.debug("CustomerListController에서 CustomerListController -> customerlist: "+customerList);
		
		model.addAttribute("customerList", customerList);
		model.addAttribute("blackCustomerList", blackCustomerList);
		model.addAttribute("beginRow", beginRow);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		
		return "getCustomerList";
	}
}
