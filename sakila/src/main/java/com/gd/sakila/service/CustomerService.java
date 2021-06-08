package com.gd.sakila.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CustomerMapper;
import com.gd.sakila.vo.CustomerList;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CustomerService {
   @Autowired 
   CustomerMapper customerMapper;
   
   // 고객 추가
   public int addCustomer(Map<String, Object> map) {
	   
	   return customerMapper.insertCustomer(map);
   }
   
   
   
   // customer 리스트 구현
   public List<CustomerList> getCustomerList(Map<String, Object> map) {
	   log.debug("*************CustomerService에서 CustomerService -> map :"+map);
	  
	   return customerMapper.selectCustomerList(map);
   }
   
   // customer total 구현
   public int getCustomerTotal(String searchWord) {
	   log.debug("*************CustomerService에서 CustomerService -> searchWord :"+searchWord);
	  
	   return customerMapper.selectCustomerTotal(searchWord);
   }
   
   
   // 블랙리스트 구현
   public List<Map<String, Object>> getBlackCustomerList() {

	   return customerMapper.selectBlackCustomerList();
   }
   
   // 고객 상세보기
   public Map<String, Object> getCustomerOne(Integer ID) {
	  log.debug("*************CustomerService에서 getCustomerOne -> ID :"+ID);
	   
	  return customerMapper.selectCustomerOne(ID);
   }
  
   
   
   public void modifyCustomerActiveByScheduler() {
      log.debug("▶▶▶▶▶▶▶ modifyCustomerActiveByScheduler() 실행");
      int row = customerMapper.updateCustomerActiveByScheduler();
      log.debug("▶▶▶▶▶▶▶ modifyCustomerActiveByScheduler() 휴면고객 처리 행 수 : " + row);
   }
}
