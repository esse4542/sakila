package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.PageParam;

@Service
@Transactional
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}

	public Map<String, Object> getBoardOne(int boardId) { // 전체적으로 통일하기 위해서 만든다
	      return boardMapper.selectBoardOne(boardId);
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
