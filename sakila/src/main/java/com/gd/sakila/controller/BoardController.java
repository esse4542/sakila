package com.gd.sakila.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.BoardService;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 컴포넌트로 객체가 자동으로 만들어진다. 서블릿처럼 행동하는 클래스를 상속받음
@RequestMapping("/admin")
public class BoardController {
   
   @Autowired // nullpointException이 발생 -> Autowired 에노테이션을 통해 객체를 주입 시켜준다
   private BoardService boardService;
   
   
   // 추가
   @GetMapping("/addBoard")
   public String addBoard() {
	   return "addBoard";
   }
   
   @PostMapping("/addBoard")
   public String addBoard(BoardForm boardForm) { // 커맨드객체
	   log.debug("boardForm :"+boardForm.toString());
	   boardService.addBoard(boardForm); // param board -> boardForm 으로 변경
	   return "redirect:/admin/getBoardList";
   }
   
   
   
   // 수정 폼
   @GetMapping("/modifyBoard")
   public String modifyBoard(Model model, @RequestParam(value = "boardId", required = true) int boardId) {
	   log.debug("modifyBoard() param : " +boardId); //디버깅
	   
	   // select
	   Map<String, Object> map = boardService.getBoardOne(boardId);
	   model.addAttribute("boardMap", map.get("boardMap"));
	   return "modifyBoard";
   }
   
   // 수정 액션
   @PostMapping("/modifyBoard")
   public String modifyBoard(Board board) {
	   log.debug("modifyBoard() param : " +board.toString()); //디버깅
	 
	   // update
	   int row = boardService.modifyBoard(board);
	   log.debug("update row : " +row);
	
	   return "redirect:/admin/getBoardOne?boardId=" +board.getBoardId();
   }
   
   
   
   // 삭제 폼
   // 리턴타입 뷰이름 문자열 C -> V
   @GetMapping("/removeBoard")
   public String removeBoard(Model model, @RequestParam(value = "boardId", required = true) int boardId) {
	   log.debug("param " +boardId); //디버깅
	   model.addAttribute("boardId", boardId);
	   return "removeBoard";
   }
   // 삭제 액션
   // C -> M -> redirect(C)
   @PostMapping("/removeBoard")
   public String removeBoard(Board board) {
	   int row = boardService.removeBoard(board);
	   log.debug("removeBoard(): " + row);
	   if(row == 0) {
	         return "redirect:/getBoardOne?boardId=" + board.getBoardId();
	      }
	   return "redirect:/admin/getBoardList";
   }

   

   
   @GetMapping("/getBoardOne")
   public String getBoardOne(Model model, 
           @RequestParam(value="boardId", required = true) int boardId) { // View가 있으면 모델이 존재
	// 디버깅
	log.debug("controller-> getBoardOne 에서 boardId : "+boardId);
	
	Map<String, Object> map = boardService.getBoardOne(boardId);
	System.out.println("@@@@@@@@@@@@@@@@@@@"+ map);
	
	model.addAttribute("boardMap", map.get("boardMap"));
    model.addAttribute("commentList", map.get("commentList"));
    
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