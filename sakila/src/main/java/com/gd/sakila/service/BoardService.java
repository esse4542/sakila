package com.gd.sakila.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.mapper.CommentMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.BoardForm;
import com.gd.sakila.vo.Boardfile;
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
	@Autowired
	BoardfileMapper boardfileMapper;
	

	// getBoardOne
	public Map<String, Object> getBoardOne(int boardId) { // 전체적으로 통일하기 위해서 만든다
		log.debug("getBoardOne 에서 boardId : "+ boardId);
		
		// 1) 상세 보기
		Map<String, Object> boardMap = boardMapper.selectBoardOne(boardId);
		log.debug("selectBoardOne 에서 boardMap: "+boardMap);
		
		// 2) boardfile 목록
		List<Boardfile> boardfileList = boardfileMapper.selectBoardfileByBoardId(boardId); //추가
		//log.debug("selectBoardfileByBoardId 에서 boardfileList: "+boardfileList);
		
		// 3) 댓글 목록
		List<Comment> commentList = commentMapper.selectCommentListByBoard(boardId);
		log.debug("commentList에서 size() "+commentList.size());
		
		Map<String, Object> map = new HashMap<>();
	    map.put("boardMap", boardMap);
	    map.put("boardfileList", boardfileList);
		map.put("commentList", commentList);
		
		return map;
	}

	
	
	
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
	public void addBoard(BoardForm boardForm) { // void로 바꿔줌.
		// boardForm --> board, boardfile
		log.debug("addBoard() Param boardForm :"+boardForm);
	
		//1)
		Board board = boardForm.getBoard(); // boardId값이 null
		log.debug("addBoard()에서 board :"+board.getBoardId()); // 0
		
		boardMapper.insertBoard(board); 
		// 입력시 만들어진 key값을 리턴받아야 한다. -> 리턴받을수 없다. -> 매개변수 board의 boardId값 변경해준다.
		log.debug("addBoard() boardId :"+board.getBoardId()); // auto increment로 입력된 값
		
		
		// 2)
		List<MultipartFile> list = boardForm.getBoardfile();
		if(list != null) {
			for(MultipartFile f : list) {
				Boardfile boardfile = new Boardfile();
				boardfile.setBoardId(board.getBoardId()); // auto increment로 입력된 값
				// test.txt -> newname.txt
				
				String originalFilename = f.getOriginalFilename();
				int pindex = originalFilename.lastIndexOf("."); // 4
				String ext = originalFilename.substring(pindex).toLowerCase(); // .txt
				String prename = UUID.randomUUID().toString().replace("-", "");
				
				String filename = prename+ext;
				boardfile.setBoardfileName(filename); // 이슈>>>> 중복으로 인해 덮어쓰기 가능
				boardfile.setBoardfileSize(f.getSize());
				boardfile.setBoardfileType(f.getContentType());
				log.debug("addBoard()에서 boardfile :"+boardfile);
				
				// 2-1)
				boardfileMapper.insertBoardfile(boardfile);
				
				// 2-2)
				// 파일을 저장
				try {	
						// 파일 저장하기 추가
						File temp = new File(""); // 프로젝트 폴더에 빈 파일 만들어짐.
						String path = temp.getAbsolutePath(); // 프로젝트 폴더
						f.transferTo(new File(path+"\\src\\main\\webapp\\resource\\"+filename)); // 경로
				} catch (Exception e) {
						throw new RuntimeException();
				} 
			}
		}

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
