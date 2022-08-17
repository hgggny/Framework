package com.kh.mybatis.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public interface BoardDao {

	int getBoardCount(SqlSession session);

	int getBoardCount(SqlSession session, String writer, String title, String content);
	
	int getBoardCount(SqlSession session, String[] filters);
	
	List<Board> findAll(SqlSession session, PageInfo pageInfo);

	List<Board> findAll(SqlSession session, PageInfo pageInfo, String writer, String title, String content);

	List<Board> findAll(SqlSession session, PageInfo pageInfo, String[] filters);

	Board findBoardByNo(SqlSession session, int no);



}
