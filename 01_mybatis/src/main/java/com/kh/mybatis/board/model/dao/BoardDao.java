package com.kh.mybatis.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public interface BoardDao {

	int getBoardCount(SqlSession session);

	List<Board> findAll(SqlSession session, PageInfo pageInfo);

	List<Board> findAll(SqlSession session, String writer, String title, String content);

}
