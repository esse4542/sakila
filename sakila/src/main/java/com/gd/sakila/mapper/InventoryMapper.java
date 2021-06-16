package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper {
	 //재고 삭제
	int deleteInventory(int inventoryId);
	//재고 추가
	int insertInventory(Map<String, Object> map);
	// 재고 total
	int selectInventoryTotal(Map<String, Object> map);
	// 재고 목록
	List<Map<String, Object>> selectInventoryList(Map<String, Object> map);
}
