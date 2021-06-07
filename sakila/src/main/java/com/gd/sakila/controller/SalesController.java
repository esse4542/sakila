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

import com.gd.sakila.service.SalesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class SalesController {
	@Autowired
	SalesService salesService;
	
	@GetMapping("getSalesList")
	public String getSalesList(Model model, @RequestParam(value = "storeId", required = false) Integer storeId) {
		log.debug("*************SalesController에서 getSalesList -> storeId :"+storeId);
		
		// 카테고리별
		if(storeId != null && storeId == 0) { 
			storeId = null;
		}
		
		Map<String, Object> map = new HashMap<>();
		log.debug("*************SalesController에서 getSalesList -> map :"+map);
		map.put("storeId", storeId);
		
		List<Map<String, Object>> bestSellerList = salesService.getBestSellerTop10();
		log.debug("*************SalesController에서 getSalesList -> bestSellerList :"+bestSellerList);
		
		List<Map<String, Object>> salesByCategoryList = salesService.getSalesByCategoryList();
		log.debug("*************SalesController에서 getSalesList -> salesByCategoryList :"+salesByCategoryList);
		
		List<Map<String, Object>> monthlySalesList = salesService.getmonthlySalesList(map);
		log.debug("*************SalesController에서 getSalesList -> monthlySalesList :"+monthlySalesList);
		
		// model
		model.addAttribute("bestSellerList", bestSellerList);
		model.addAttribute("salesByCategoryList", salesByCategoryList);
		model.addAttribute("monthlySalesList", monthlySalesList);
		model.addAttribute("storeId", storeId);
		
		
		return "getSalesList";
	}
	
}
