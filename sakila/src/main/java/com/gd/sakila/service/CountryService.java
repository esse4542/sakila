package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CountryMapper;
import com.gd.sakila.vo.Country;
import com.gd.sakila.vo.PageParam;

@Service
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class CountryService {
   
   // 안만들어진 클래스가 존재하고 사용해야한다면 생성자로 인해 기다려야한다.
	@Autowired
   private CountryMapper countryMapper; // spring에는 countryMapper에 객체 주입하는 기능이 있음(의존성 주입 = Dependency Injection)
   
   // spring 부팅 -> Mapper라는 에노테이션이 붙어 있으니 CountryMapper의 서브 클래스를 만듬 -> 서브 클래스의 객체를 만듬 -> 서비스 에노테이션이 있으므로 자동으로 CountryService 객체를 만드는데 그 와중에 Autowired가 있으므로 countryMapper를 먼저 찾아서(없으면 만듬) 객체 주입(bean이라고 부름)  
   
   public  Map<String, Object> getCountryList(int currentPage, int rowPerPage){
       // CountryMapper countryMapper = new CountryMapper(); // 인터페이스 객체 생성 불가
	   // beginRow
	   // 1. 컨트롤러에서 보내어준 매개값을 가공
	   int beginRow = (currentPage-1) * rowPerPage;
	   PageParam pageParam = new PageParam();
	   pageParam.setBeginRow(beginRow);
	   pageParam.setRowPerPage(rowPerPage);
      /*
       * CountryMapper countryMapper = null; List<Country> list =
       * countryMapper.selectCountryList(); // nullpointException
       */
      // 2. dao 호출
      List<Country> list = countryMapper.selectCountryList(pageParam);
      int total = countryMapper.selectCountryTotal();
      
      // 3. dao 반환값을 가공
      int lastPage = total / rowPerPage;
      if(total % rowPerPage != 0) { // 나누어 떨어지면
    	  lastPage += 1;
      }
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("list", list);
      map.put("lastPage", lastPage);
      return map;
   }
}