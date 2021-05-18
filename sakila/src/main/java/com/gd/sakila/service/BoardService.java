package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.Comment;
import com.gd.sakila.vo.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	CommentMapper commentMapper;
	
	
	// boardUpadte( 수정 액션 )
	public int modifyBoard(Board board) {
		log.debug("modifyBoard 에서 board : "+ board.toString());
		return boardMapper.updateBoard(board);
	}
	
	
	// boardOne( 삭제 액션 )
	public int removeBoard(Board board) {
		// 디버깅
		log.debug("removeBoard() 에서 board : "+ board.toString());
		
		
		// 1. 게시글 삭제
		int boardRow = boardMapper.deleteBoard(board);
		if(boardRow == 0) {
			return 0;
		}
		// 디버깅
		log.debug("@@@@@@ removeBoard()에서 boardRow : " +boardRow);

		
		// 2. 댓글 삭제
		int commentRow = commentMapper.deleteCommentByBoardId(boardRow);
		// 디버깅
		log.debug("@@@@@@@ removeBoard()에서 commentRow : " +commentRow);
		
		/*
		// 댓글 하나씩만 삭제
		int commentDelete = commentMapper.deleteCommentByCommentId(commentRow);
		log.debug("@@@@@@@@ removeBoard()에서 commentDelete : " +commentDelete);
		*/
		
		return boardRow;
	}
	
	
	
	// addBoard
	public int addBoard(Board board) {
		log.debug("addBoard 에서 board : "+ board.toString());
		return boardMapper.insertBoard(board);
	}


	// getBoardOne
	public Map<String, Object> getBoardOne(int boardId) { // 전체적으로 통일하기 위해서 만든다
		log.debug("getBoardOne 에서 boardId : "+ boardId);
		// 1) 상세 보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("selectBoardOne 에서 boardMap: "+boardMap);
		// 2) 댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("commentList에서 size() "+commentList.size());
		
		Map<String, Object> map = new HashMap<>();
	    map.put("boardMap", boardMap);
		map.put("commentList", commentList);
		
		return map;
	}


	// 
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String searchWord) {
		// 1번
		int boardTotal = boardMapper.selectBoardTotal(searchWord); // searchWord가 필요함
		
		/*
		int lastPage = boardTotal / rowPerPage;
		if(boardTotal / rowPerPage != 0) { //나누어 떨어지지 않으면
			lastPage++; 
		}
		*/
		
		int lastPage = (int)(Math.ceil((double)boardTotal / rowPerPage)); //이렇게 만들어도 된다.
		
		// 2번
		PageParam page = new PageParam();
		page.setBeginRow((currentPage-1)*rowPerPage);
		page.setRowPerPage(rowPerPage);
		page.setSearchWord(searchWord);
		List<Board> boardList = boardMapper.selectBoardList(page); // page
		
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("boardTotal", boardTotal);
		map.put("lastPage", lastPage);
		map.put("boardList", boardList);
		
		return map;
	}
}
