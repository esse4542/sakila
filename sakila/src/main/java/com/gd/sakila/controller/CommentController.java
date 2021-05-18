package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.CommentService;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequestMapping("/admin")
public class CommentController {
	@Autowired CommentService commentService;
	
	
	// addComment( 댓글 추가 ) 
	@GetMapping("/addComment")
	public String addComment(Comment comment, @RequestParam(value="boardId", required = true) int boardId) {
		// 디버깅
		log.debug("CommentController -> addComment에서  boardId : " + boardId);
		log.debug("CommentController -> addComment에서  comment : " + comment.toString());
		
		comment.setBoardId(boardId);
		int commentRow = commentService.addComment(comment);
		
		//디버깅
		log.debug("CommentController -> addComment의 commentRow" + commentRow);
		// redirect
		return "redirect:/admin/getBoardOne?boardId=" + boardId;
	}
	
	
	
	// deleteComment( 댓글 삭제 )
	@GetMapping("/deleteCommentByCommentId")
	public String deleteComment(@RequestParam(value="commentId", required = true) int commentId,
								@RequestParam(value="boardId", required = true) int boardId) {
		
		// 디버깅
		log.debug("CommentController -> deleteCommentByCommentId에서  boardId : " + boardId);
		log.debug("CommentController -> deleteCommentByCommentId에서  commentId : " + commentId);
		// 
		int commentRow = commentService.deleteCommentByCommentId(commentId);
		
		// 디버깅
		log.debug("CommentController -> deleteCommentByCommentId에서  commentRow" + commentRow);
		// redirect
		return "redirect:/admin/getBoardOne?boardId=" + boardId;
	}
}