package com.gd.sakila.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gd.sakila.vo.Comment;

@Mapper
public interface CommentMapper {
	// boardOne을 삭제하면서 모든 댓글 삭제
	int deleteCommentByBoardId(int boardId);
	// 댓글 하나씩 삭제
	int deleteCommentByCommentId(int commentId);
	// 댓글 입력
	int insertComment(Comment comment);
	// 댓글 리스트
	List<Comment> selectCommentListByBoard(int boardId);
}
