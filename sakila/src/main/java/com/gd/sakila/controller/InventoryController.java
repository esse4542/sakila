package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class InventoryController {
	@Autowired
	InventoryService inventoryService;
	
	// 재고 삭제
	@GetMapping("/removeInventory")
	public String removeInventory(Model model, @RequestParam(value = "inventoryId", required = false) Integer inventoryId) {
		log.debug("*************InventoryController에서 removeInventory -> inventoryId :"+inventoryId);
		
		model.addAttribute("inventoryId", inventoryId);
		return "removeInventory";
	}
	
	@PostMapping("removeInventory")
	public String removeInventory(@RequestParam(value = "inventoryId", required = false) Integer inventoryId) {	
		log.debug("*************InventoryController에서 removeInventory -> inventoryId :" + inventoryId);
		
		// 삭제 
		int deleteRow = inventoryService.removeInventory(inventoryId);
		log.debug("*************InventoryController에서 removeInventory -> inventoryId :"+deleteRow);
		
		// deleteRow가 0이면
		if(deleteRow == 0) {
			return "redirect:/admin/removeInventory";
		}
		return "redirect:/admin/getInventoryList";
	}
	
	// 재고 추가
	@GetMapping("/addInventory")
	public String addInventory() {
		
		return "addInventory";
	}
	
	@PostMapping("/addInventory")
	public String addInventory(Model model, @RequestParam(value = "filmId", required = true)int filmId,
								@RequestParam(value = "storeId", required = true) int storeId) {
		log.debug("*************InventoryController에서 addInventory -> filmId :" + filmId);
		log.debug("*************InventoryController에서 addInventory -> storeId :" + storeId);
		
		// map 만들어주기
		Map<String, Object> map = new HashMap<>();
		map.put("filmId", filmId);
		map.put("storeId", storeId);
		
		// inventoryService 불러오기
		int returnMap = inventoryService.insertInventory(map);
		log.debug("*************InventoryController에서 addInventory -> returnMap :" + returnMap);
		
		model.addAttribute("storeId", storeId);

		return "redirect:/admin/getInventoryList";
	}

	
	
	
	@GetMapping("/getInventoryList")
	public String getInventoryList(Model model, @RequestParam(value = "storeId", required = false ) Integer storeId,
											@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
											@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
											@RequestParam(value = "searchWord", required = false) String searchWord) {
		log.debug("*************InventoryController에서 getInventoryList -> storeId :"+storeId);
		log.debug("*************InventoryController에서 getInventoryList -> currentPage :"+currentPage);
		log.debug("*************InventoryController에서 getInventoryList -> rowPerPage :"+rowPerPage);
		log.debug("*************InventoryController에서 getInventoryList -> searchWord :"+searchWord);
		
		// 검색과 카테고리 코드(카테고리 나오려면 해줘야함)
		if (searchWord != null && searchWord.equals("")) { 
			searchWord = null;
		}
		if(storeId != null && storeId == 0) {
			storeId = null;
		}
		
		
		
		// beginRow
		int beginRow = (currentPage-1)* rowPerPage;
		log.debug("*************InventoryController에서 getInventoryList -> beginRow :"+beginRow);
		
		
		// map에 파라미터 데이터를 넣어줌
		Map<String, Object> map = new HashMap<>();
		log.debug("*************InventoryController에서 getInventoryList -> map :"+map);
		map.put("storeId", storeId); // storeId를 넘겨줘야함.
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", searchWord);
		map.put("beginRow", beginRow);
		
		
		
		// selectInventoryTotal
		int inventoryTotal = inventoryService.getInventoryTotal(map);
		log.debug("*************InventoryController에서 getInventoryList -> inventoryTotal :"+inventoryTotal);
		
		
		// lastPage
		int lastPage = inventoryTotal / rowPerPage;
		if(inventoryTotal / rowPerPage != 0) {
			lastPage++; 
		}
		log.debug("*************InventoryController에서 getInventoryList -> lastPage :"+lastPage);
		
		
		// inventoryList
		List<Map<String, Object>> inventoryList = inventoryService.getInventoryList(map);
		log.debug("*************InventoryController에서 getInventoryList -> inventoryList :"+inventoryList);
		
		model.addAttribute("inventoryList", inventoryList);
		model.addAttribute("beginRow", beginRow);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("storeId",storeId);
		
		return "getInventoryList";
	}
}
