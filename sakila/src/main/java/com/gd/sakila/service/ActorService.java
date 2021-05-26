package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.ActorMapper;
import com.gd.sakila.vo.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ActorService {
	@Autowired
	ActorMapper actorMapper;
	
	public Map<String, Object> getActorList(int currentPage, int rowPerPage, String searchWord) {
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> currentPage : "+currentPage);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> rowPerPage : "+rowPerPage);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> searchWord : "+searchWord);
		
		int actorTotal = actorMapper.selectActorTotal(searchWord);
		int lastPage = (int)(Math.ceil((double)actorTotal/ rowPerPage));
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> actorTotal : "+actorTotal);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> lastPage : "+lastPage);
		
		Map<String, Object> actorMap = new HashMap<>();
		int beginRow = ((currentPage-1)*rowPerPage);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> beginRow : "+beginRow);
		
		actorMap.put("currentPage", currentPage);
		actorMap.put("rowPerPage", rowPerPage);
		actorMap.put("searchWord", searchWord);
		actorMap.put("beginRow", beginRow);
		
		List<Map<String, Object>> actorList = actorMapper.selectActorInfoList(actorMap);
		log.debug("▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆▼◆◆ ActorService에서 getActorList -> actorList : "+actorList.toString());
		
	
		// 컨트롤러에서 사용 할 수 있는 맵
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("actorTotal", actorTotal);
		returnMap.put("actorList", actorList);
		returnMap.put("beginRow", beginRow);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
	
	
	
	public int addActor(Actor actor) {
		log.debug("ActorService에 있는 addActor -> Actor : "+actor);
		
		
		return actorMapper.insertActor(actor);
	}
}
