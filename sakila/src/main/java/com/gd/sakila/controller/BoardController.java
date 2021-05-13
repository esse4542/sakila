package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.BoardService;
import com.gd.sakila.vo.Board;

@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
public class BoardController {
   
   @Autowired // nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
   private BoardService boardService;
   
   @GetMapping("/addBoard")
   public String addBoard() {
	   return "addBoard";
   }
   
   @PostMapping("/addBoard")
   public String addBoard(Board board) { // 커맨드객체
	   boardService.addBoard(board);
	   return "redirect:/getBoardList";
   }
   

   
   @GetMapping("/getBoardOne")
   public String getBoardOne(Model model, 
           @RequestParam(value="boardId", required = true) int boardId) { // View가 있으면 모델이 존재
	Map<String, Object> map = boardService.getBoardOne(boardId);
	System.out.println(map);
	model.addAttribute("map", map);
	return "getBoardOne";
	}

   
   @GetMapping("/getBoardList")
   public String getBoardList(Model model, @RequestParam(value="currentPage", defaultValue = "1") int currentPage, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage, @RequestParam(value="searchWord", required = false) String searchWord) {
      
      // 디버깅
      System.out.println(currentPage + "<-- currentPage");
      System.out.println(rowPerPage + "<-- rowPerPage");
      System.out.println(searchWord + "<-- searchWord");
      
      Map<String,Object> map = boardService.getBoardList(currentPage, rowPerPage, searchWord);
      // view 작업의 편의성을 위해 풀어서 보내준다
      //model.addAttribute("map", map);
      model.addAttribute("boardList", map.get("boardList"));
      model.addAttribute("searchWord", searchWord);
      model.addAttribute("lastPage", map.get("lastPage"));
      model.addAttribute("currentPage", currentPage);
      
      return "getBoardList";
   }
}