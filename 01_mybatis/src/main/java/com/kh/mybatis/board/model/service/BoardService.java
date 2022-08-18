package com.kh.mybatis.board.model.service;

import java.util.List;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public interface BoardService {

	int getBoardCount();

	int getBoardCount(String writer, String title, String content);
	
	int getBoardCount(String[] filters);
	
	List<Board> findAll(PageInfo pageInfo);

	List<Board> findAll(PageInfo pageInfo, String[] filters);
	
	List<Board> findAll(PageInfo pageInfo, String writer, String title, String content);

	Board findBoardByNo(int no);

	int save(Board board);

	int delete(int no);
}
