package com.gd.sakila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	//Logger log = LoggerFactory.getLogger(this.getClass()); // 항상 this.getClass() 로 적어줌, 매개변수를 자기 클래스로 들어가면 된다.
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		//System.out.println("home..."); //"/home", "index" 이게 나와도 home으로 출력
		log.debug("test");
		return "home";
	}
}
