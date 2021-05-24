package com.gd.sakila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gd.sakila.service.CustomerService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 특정 능력이 없기때문에 component라고 부름.
public class SakilaScheduler {
	@Autowired
	CustomerService customerService;
	
	// Scheduled 매소드는 void반환, 매개변수 0개
	// 0 0 0 : 24시, 1 : 1일, * : 매달, * : 요일은 상관없다.
	@Scheduled(cron = "0 58 11 24 * *")
	public void modifyCustomerActive() {
		customerService.modifyCustomerActiveByScheduler();
		log.debug("@@@@@@@ modifyCustomerActive 스케줄러 실행 완료!");
	}
}
