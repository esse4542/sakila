package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.PageParam;

@Mapper
public interface FilmMapper {
	// TOTAL
	int selectFilmTotal(String searchWord); 
	// FilmOne
	Map<String, Object> selectFilmOne(int filmId);
	// FilmList
	List<Map<String, Object>> selectFilmList(Map<String, Object> map);

	List<Integer> selectFilmInStock(Map<String, Object> map); // int가 아니라 List<Integer>로 받아야함.
}
