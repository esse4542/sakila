package com.gd.sakila.controller;

import java.util.List;
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
	
	
	// 필름 리스트
	@GetMapping("/getFilmList")
	public String getFilmList(Model model, @RequestParam(name = "categoryName", required = false) String categoryName, //카테고리 네임이 넘어온다. (g하지만 안 넘어올 수도 있기때문에false)
											@RequestParam(name = "price", required =  false) Double price,
											@RequestParam(name = "searchWord", required =  false) String searchWord,
											@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
											@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
											@RequestParam(name = "rating", required = false) String rating,
											@RequestParam(name = "searchActors", required = false) String searchActors) { 
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> categoryName : "+categoryName);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> price : "+price);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> searchWord : "+searchWord);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> currentPage : "+currentPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> rowPerPage : "+rowPerPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> rating : "+rating);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> searchActors : "+searchActors);
		
		// 카테고리를 선택하지 않고 검색했을 경우
		if(categoryName != null && categoryName.equals("")) {
			categoryName = null;
		}
		// price
		if(price != null && price==0) {
			price = null;
		}
		// title(searchWord)
		if(searchWord != null && searchWord.equals("")) {
			searchWord = null;
		}
		// price
		if(rating != null && rating.equals("")) {
			rating = null;
		}		
		
		
		Map<String, Object> map = filmService.getFilmList(categoryName, price, searchWord, currentPage, rowPerPage, rating, searchActors); // 16개 이름 또는 null
		//log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmList -> filmList.size : " +filmList.size());
		model.addAttribute("filmList", map.get("filmList"));
		model.addAttribute("categoryNameList", map.get("categoryNameList"));
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("price", price);
		model.addAttribute("title", searchWord); // 검색어도 생성
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("rating", rating);
		model.addAttribute("searchActors", searchActors);
		
		return "getFilmList";
	}
	
	
	
	/*
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
	*/
	
	
	@GetMapping("/getFilmOne")
	public String getFilemOne(Model model, @RequestParam(value="filmId", required=true) int filmId, 
											@RequestParam(value="storeId", defaultValue="1",required=true) int storeId,
											@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
											@RequestParam(value="searchWord", required = false ) String searchWord,
											@RequestParam(value="searchActor", required = false ) String searchActor,
											@RequestParam(value="category", required = false ) String category,
											@RequestParam(value="rating", required = false ) String rating,
											@RequestParam(value="rentalRate", required = false ) String rentalRate) {
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> category : "+category);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> searchWord : "+searchWord);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> currentPage : "+currentPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> filmId : "+filmId);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> rating : "+rating);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> storeId : "+storeId);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> rentalRate : "+rentalRate);
		
		Map<String, Object> map = filmService.getFilmOne(filmId, 1); // 1,1 넣으면 filmCount는 4가 나와야함. 
		model.addAttribute("filmId", filmId);
		model.addAttribute("store1Stock", map.get("store1Stock"));
		model.addAttribute("store2Stock", map.get("store2Stock"));
		model.addAttribute("filmMap", map.get("filmMap"));
		model.addAttribute("filmCount", map.get("filmCount"));
		
		
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> filmId : " +filmId);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> storeId : " +storeId);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> filmMap : " +map.get("filmMap"));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmController에 있는 getFilmOne -> filmCount : " +map.get("filmCount"));
		
		return "getFilmOne";
	}
}
