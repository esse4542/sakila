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
	
	
	//수정 및 삭제 (배우)
	public void modifyFilmActor(Map<String, Object> map) {
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 modifyFilmActor -> map : "+map);
		
		// delete (삭제)
		int deleteActor = filmMapper.deleteActor((int)map.get("filmId"));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 modifyFilmActor -> deleteActor : "+deleteActor);
		
		if(map.get("actorId") != null ) {
			for(int atoar : (int[])map.get("actorId")) {
				log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 modifyFilmActor -> atoar : "+atoar);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("actorId", atoar);
				paramMap.put("filmId", map.get("filmId"));
				
				// 입력(수정) 넣기
				int insertActor = filmMapper.insertActor(paramMap);
				log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 modifyFilmActor -> insertActor : "+insertActor);
				
			}
		}
	}
	
	
	
	// 배우 전체 보여주기
	public List<Map<String, Object>> getFilmActorListByFilm(int filmId) {
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에서 getFilmActorListByFilm -> filmId"+filmId);
		
		return filmMapper.selectFilmActorListByFilm(filmId);
	}
	
	
	
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
		
		// filmOne 호출
		Map<String,Object> filmMap = filmMapper.selectFilmOne(filmId);
		//filmMap.put("filmCount", paramMap.get("filmCount"));
		//filmMap.put("filmMap", filmMap);
		
		
		// 1번 재고량
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("filmId", filmId);
		paramMap1.put("storeId", 1);
		int filmCount1 = 0;
		paramMap1.put("filmCount", filmCount1);
		
	
		List<Integer> store1Stock  = filmMapper.selectFilmInStock(paramMap1); // 프로시저 호출
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmCount : "+ paramMap1.get("filmCount"));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> store1Stock  : " +store1Stock );
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmMap : " +filmMap);
		
		
		// 2번 재고량
		// 1번 재고량
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("filmId", filmId);
		paramMap2.put("storeId", 2);
		int filmCount2 = 0;
		paramMap2.put("filmCount", filmCount2);
		
	
		List<Integer> store2Stock  = filmMapper.selectFilmInStock(paramMap2); // 프로시저 호출
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmCount : "+ paramMap2.get("filmCount"));
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> list : " +store2Stock );
		log.debug("◆■◆◆■◆◆■◆◆■◆◆■◆◆■◆ FilmService에 있는 getFilmOne -> filmMap : " +filmMap);
		
		
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("filmMap", filmMap);
		returnMap.put("store1Stock", paramMap1.get("filmCount"));
		returnMap.put("store2Stock", paramMap2.get("filmCount"));
		
		return returnMap;
	}
}
