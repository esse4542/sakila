package com.gd.sakila.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	StaffService staffService;
	
	
	//Logger log = LoggerFactory.getLogger(this.getClass()); // 항상 this.getClass() 로 적어줌, 매개변수를 자기 클래스로 들어가면 된다.
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		//System.out.println("home..."); //"/home", "index" 이게 나와도 home으로 출력
		log.debug("test");
		return "home"; //로그인
	}
	
	
	// 로그아웃
	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	// 로그인
	@PostMapping("/login")
	public String login(HttpSession session, Staff staff) { // servlet 세션을 직접사용, 컨트롤러 매소드의 매개변수 
		log.debug("login() param staff : " +staff);
		
		Staff loginStaff = staffService.login(staff);
		log.debug("login() return loginStaff : "+loginStaff);
		
		if(loginStaff != null) { // 로그인 
			session.setAttribute("loginStaff", loginStaff);
		} 
		
		return "redirect:/";
	}
}
