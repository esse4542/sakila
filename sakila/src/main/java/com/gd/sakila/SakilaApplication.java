package com.gd.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@SpringBootApplication
@EnableScheduling // 이게 있어야 SakilaApplication 찾을 준비 할 수 잇음 , 스케줄링을 위해 추가(?)
public class SakilaApplication { // @ServletComponentScan 추가 해서 @WebFilter를 찾을 수 있음
	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
	}

}
