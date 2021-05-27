package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.ActorService;
import com.gd.sakila.vo.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class ActorController {
	@Autowired
	ActorService actorService;
	
	
	
	@GetMapping("/addActor")
	public String addActor() {
		return "addActor";
	}
	@PostMapping("/addActor")
	public String addActor(Actor actor) {
		actorService.addActor(actor);
		return "redirect:/admin/getActorList";
	}
	

	
	@GetMapping("/getActorList") // actor_view
	public String getActorList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
								@RequestParam(value = "searchWord", required =  false) String searchWord) {
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorController에서 getActorList -> currentPage : "+currentPage);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorController에서 getActorList -> rowPerPage : "+rowPerPage);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorController에서 getActorList -> searchWord : "+searchWord);

		
		if(searchWord != null && searchWord.equals("")) {
			searchWord = null;
		}
		
		Map<String, Object> map = actorService.getActorList(currentPage, rowPerPage, searchWord);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorController에서 getActorList -> map : "+ map.get("actorList").toString());
		
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("actorList", map.get("actorList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		
		return "getActorList";
	}
}
