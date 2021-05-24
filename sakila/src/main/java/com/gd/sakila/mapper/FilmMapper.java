package com.gd.sakila.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.FilmView;
import com.gd.sakila.vo.PageParam;

@Mapper
public interface FilmMapper {
	int selectFilmTotal(String searchWord); 
	List<FilmView> selectFilmList(PageParam page);
	List<Integer> selectFilmInStock(Map<String, Object> map); // int가 아니라 List<Integer>로 받아야함.
}
