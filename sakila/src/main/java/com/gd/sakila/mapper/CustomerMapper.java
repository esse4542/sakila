package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.CustomerList;

@Mapper
public interface CustomerMapper {
	// 블랙리스트
	List<Map<String, Object>> selectBlackCustomerList();
	// 전체 고객 리스트
	List<CustomerList> selectCustomerList(Map<String, Object> map);
	// TOTAL
	int selectCustomerTotal(String searchWord);
	// 
	int updateCustomerActiveByScheduler();
	// 고객 상세보기
	Map<String, Object> selectCustomerOne(int ID); // 상세보기는 map<,>으로
}
