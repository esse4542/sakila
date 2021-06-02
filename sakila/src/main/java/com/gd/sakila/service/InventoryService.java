package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.InventoryMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InventoryService {
	@Autowired
	InventoryMapper inventoryMapper;
	
	// 재고 목록
	public List<Map<String, Object>> getInventoryList(Map<String, Object> map) {
		log.debug("*************InventoryService에서 getInventoryList -> map :"+map);
		
		return inventoryMapper.selectInventoryList(map);
	}
	
	// 재고 토탈
	public int getInventoryTotal(Map<String, Object> map) {
		log.debug("*************InventoryService에서 getInventoryTotal -> map :"+map);
		
		return inventoryMapper.selectInventoryTotal(map);
	}
}
