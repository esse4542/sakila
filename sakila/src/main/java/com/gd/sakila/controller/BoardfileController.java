package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.service.BoardfileService;
import com.gd.sakila.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class BoardfileController {
	@Autowired
	BoardfileService boardfileService;
	
	// 파일 하나씩 입력 폼
	@GetMapping("/addBoardfile")
	public String addBoardfile(Model model, @RequestParam(value = "boardId", required = true) int boardId ) {
		model.addAttribute("boardId", boardId);
		return "addBoardfile";
	}
	
	// 파일 하나씩 입력 액션
	@PostMapping("/addBoardfile")
	public String addBoard(MultipartFile multipartFile, @RequestParam(value = "boardId", required = true) int boardId ) {
		log.debug("%%%%%%%%%%%%%%%%% boardId : "+boardId);
		log.debug("%%%%%%%%%%%%%%%%% multipartFile" + multipartFile);
		boardfileService.addBoardfile(multipartFile, boardId);
		// 입력 성공할 경우
		return "redirect:/admin/getBoardOne?boardId="+boardId;
	}
	
	
	
	
	// boardfile 삭제 (하나씩)
	@GetMapping("/removeBoardfile") // 앞에 /admin안 적으려고  @RequestMapping("/admin") 사용
	public String removeBoardfile(Boardfile boardfile) {
		// 아무나(글쓴이가 아니라도) 삭제 가능
		boardfileService.removeBoardfileOne(boardfile);
		return "redirect:/admin/getBoardOne?boardId="+boardfile.getBoardId();
	}
}
