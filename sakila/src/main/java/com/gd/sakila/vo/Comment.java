package com.gd.sakila.vo;

import lombok.Data;

@Data
public class Comment {
	private int commentId;
	private int boardId;
	private String username;
	private String commeentContnet;
	private String insertDate;
}
