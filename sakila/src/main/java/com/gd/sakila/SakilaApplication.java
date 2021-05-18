package com.gd.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SakilaApplication { // @ServletComponentScan 추가 해서 @WebFilter를 찾을 수 있음

	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
		// @Controller, @Mapper, @Service, .....
	}

}
