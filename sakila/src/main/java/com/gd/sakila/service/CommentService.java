package com.gd.sakila.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
@Transactional 
public class CommentService {
	@Autowired private CommentMapper commentMapper;
	
	// 댓글 추가입력 매소드
	public int addComment(Comment comment) {
		
		// 디버깅
		log.debug("CommentService -> addComment에서 comment : " + comment.toString());
		
		return commentMapper.insertComment(comment);
	}
	
	// 댓글 삭제 매소드(하나씩)
	public int deleteCommentByCommentId(int commentId) {
		
		// 디버깅
		log.debug("CommentService -> deleteCommentByCommentId에서 commentId : " + commentId);
		
		return commentMapper.deleteCommentByCommentId(commentId);
	}
}