package com.kh.mybatis.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public interface BoardDao {
	public final static String STATUS_DISABLE = "N";

	int getBoardCount(SqlSession session);

	int getBoardCount(SqlSession session, String writer, String title, String content);
	
	int getBoardCount(SqlSession session, String[] filters);
	
	List<Board> findAll(SqlSession session, PageInfo pageInfo);

	List<Board> findAll(SqlSession session, PageInfo pageInfo, String writer, String title, String content);

	List<Board> findAll(SqlSession session, PageInfo pageInfo, String[] filters);

	Board findBoardByNo(SqlSession session, int no);

	int insertBoard(SqlSession session, Board board);

	int updateBoard(SqlSession session, Board board);
	
	int updateStatus(SqlSession session, int no, String status);

}
