package com.gd.sakila.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardfileService {
	@Autowired
	BoardfileMapper boardfileMapper;
	
	// 파일 추가
	public int addBoardfile(MultipartFile multipartFile , int boardId) {
		
		// 1. 물리적 파일 저장
		File temp = new File("");
		// 파일 프로젝트 경로
		String path = temp.getAbsolutePath(); 
		// 확장자
		int p = multipartFile.getOriginalFilename().lastIndexOf(".");
		String ext = multipartFile.getOriginalFilename().substring(p);
		// 확장자를 제외한 파일 이름
		String prename = UUID.randomUUID().toString().replace("-", "");
		File file = new File(path + "\\src\\main\\webapp\\resource\\" + prename+ext);
		try {
			multipartFile.transferTo(file); // multipart안에 파일을 반 file로 복사
		} catch (Exception e) {
			throw new RuntimeException();
		} 
	
		
		// 2. DB 입력(저장)
		Boardfile boardfile = new Boardfile();
		boardfile.setBoardId(boardId);
		boardfile.setBoardfileName(prename+ext);
		boardfile.setBoardfileSize(multipartFile.getSize());
		boardfile.setBoardfileType(multipartFile.getContentType());
		
		int row = boardfileMapper.insertBoardfile(boardfile);
		
		return row; 
		
	}
	
	
	
	
	
	// 삭제 delete
	public int removeBoardfileOne(Boardfile boardfile) {
		log.debug("BoardfileService에서 removeBoardfileOne param : "+ boardfile);
		
		// 1. 물리적 파일 삭제
		File temp = new File("");
		String path = temp.getAbsolutePath(); 
		File file = new File(path + "\\src\\main\\webapp\\resource\\" + boardfile.getBoardfileName()); // 파일 이름
		if(file.exists()) { //파일이 존재 한다면
			log.debug("BoardfileService에서 removeBoardfileOne() if문 출력 ");
			file.delete(); //파일을 가져와 삭제
		}
		
		
		// 2. DB삭제
		int row = boardfileMapper.deleteBoardfileOne(boardfile.getBoardfileId());
		log.debug("BoardfileService에서 removeBoardfileOne() 삭제 성공(1), 삭제 실패(0) : " +row);
		return row;
	}
}
