package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CategoryMapper;
import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FilmService {
	@Autowired
	FilmMapper filmMapper;
	@Autowired
	CategoryMapper categoryMapper;
	
	// 필름리스트
	public Map<String, Object> getFilmList(String categoryName, Double price, 
											String searchWord, int currentPage, int rowPerPage, String rating, String searchActors) {
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 getFilmList -> categoryName : "+categoryName);
		
		int filmTotal = filmMapper.selectFilmTotal(searchWord);
		int lastPage = (int)(Math.ceil((double)filmTotal / rowPerPage));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 getFilmList -> filmTotal : "+ filmTotal);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 getFilmList -> lastPage : "+ lastPage);
		
		Map<String, Object> paramMap = new HashMap<>();
		int beginRow = ((currentPage-1)*rowPerPage); // beginRow값을 생성해서 만들어줘야함., 만들지 않으면 쿼리 문제로 0,10이 나옴.
		
		paramMap.put("categoryName", categoryName);
		paramMap.put("price", price);
		paramMap.put("searchWord", searchWord);
		paramMap.put("currentPage", currentPage);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rating", rating);
		paramMap.put("searchActors", searchActors);
		
		
		List<Map<String, Object>> filmList = filmMapper.selectFilmList(paramMap);
		List<String> categoryNameList = categoryMapper.selectCategoryNameList();
	
		// 컨트롤러에서 사용 할 수 있는 맵
		Map<String, Object> returnMap = new HashMap<>();
	
		returnMap.put("filmList", filmList);
		returnMap.put("categoryNameList", categoryNameList);
		returnMap.put("filmTotal", filmTotal);
		returnMap.put("beginRow", beginRow); 
		returnMap.put("lastPage", lastPage); // returnMap.put로 만들어줘야 getFilmList 페이징이 구현된다.
		
		return returnMap;
	}
	
	
	/*
	// FilmList
	public Map<String, Object> selectFilmList(int currentPage, int rowPerPage, String searchWord) {
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 selectFilmList -> currentPage : " +currentPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 selectFilmList -> rowPerPage : " +rowPerPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 selectFilmList -> searchWord : " +searchWord);
		
		int filmListTotal = filmMpper.selectFilmTotal(searchWord);
		int lastPage = (int)Math.ceil((double)filmListTotal/ rowPerPage);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 selectFilmList -> filmListTotal : " +filmListTotal);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 selectFilmList -> lastPage : " +lastPage);
		
		PageParam page = new PageParam();
		page.setBeginRow((currentPage-1)*lastPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 selectFilmList -> page : " +page);
		
		List<FilmView> filmList = filmMpper.selectFilmList(page);
		
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("lastPage", lastPage);
		returnMap.put("filmList", filmList);
		return returnMap;
	}
	*/
	
	
	
	
	
	// map안에는 film, filmCount 두개를 넣기.
	public Map<String, Object> getFilmOne(int filmId, int storeId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("filmId", filmId);
		paramMap.put("storeId", storeId);
		int filmCount = 0;
		paramMap.put("filmCount", filmCount);
		
		// filmOne 호출
		Map<String,Object> filmMap = filmMapper.selectFilmOne(filmId);
		filmMap.put("filmCount", paramMap.get("filmCount"));
		filmMap.put("filmMap", filmMap);
		
		List<Integer> list = filmMapper.selectFilmInStock(paramMap); // 프로시저 호출
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmCount : "+ paramMap.get("filmCount"));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> list : " +list);
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmMap : " +filmMap);
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("filmMap", filmMap);
		return returnMap;
	}
}
