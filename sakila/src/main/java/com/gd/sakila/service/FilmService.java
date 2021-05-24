package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.FilmMapper;
import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FilmService {
	@Autowired
	FilmMapper filmMpper;
	
	
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
	
	
	
	
	
	
	// map안에는 film, filmCount 두개를 넣기.
	public Map<String, Object> getFilmOne(int filmId, int storeId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("filmId", filmId);
		paramMap.put("storeId", storeId);
		int filmCount = 0;
		paramMap.put("filmCount", filmCount);
		
		
		List<Integer> list = filmMpper.selectFilmInStock(paramMap); // 프로시저 호출
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmCount : "+ paramMap.get("filmCount"));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> list : " +list);
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		return returnMap;
	}
}
