package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.PageParam;

@Mapper // mapper라는 인터페이스를 찾는다
public interface CountryMapper {
	List<Country> selectCountryList(PageParam pageParam);
	int selectCountryTotal();
}
