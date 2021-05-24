package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class FilmController {
	@Autowired
	FilmService filmService;
	
	
	// FilmList
	@GetMapping("/getFilmList")
	public String getFilmList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
								@RequestParam(value = "searchWord", required = false) String searchWord) {
		
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> currentPage : " +currentPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> rowPerPage : " +rowPerPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> searchWord : " +searchWord);
		
		Map<String, Object> filmMap = filmService.selectFilmList(currentPage, rowPerPage, searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", filmMap.get("lastPage"));
		model.addAttribute("filmList", filmMap.get("filmList"));
		
		return "getFilmList";
		
	}
	
	
	
	@GetMapping("/getFilmOne")
	public String getFilemOne() {
		filmService.getFilmOne(1, 1); // 1,1 넣으면 filmCount는 4가 나와야함. 
		
		return "getFilmOne";
	}
}
