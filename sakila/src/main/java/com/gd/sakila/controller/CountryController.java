package com.gd.sakila.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CountryService;
import com.gd.sakila.vo.Country;


@Controller
public class CountryController {
	
	@Autowired // 이거 해야 null 안뜸, 자식 클래스를 자동으로 만들어준다고 하심. (인터페이스라 본인은 못만듬)
	private CountryService countryService;
	
	@GetMapping("/countryList")
	public String countryList(Model model,
								@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		 Map<String, Object> map = countryService.getCountryList(currentPage, rowPerPage); 
		model.addAttribute("list", map.get("list"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		return "countryList"; // 디폴트값이 포워딩
	}
}
