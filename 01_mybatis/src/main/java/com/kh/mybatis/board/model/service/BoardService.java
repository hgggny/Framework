package com.kh.mybatis.board.model.service;

import java.util.List;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public interface BoardService {

	int getBoardCount();

	List<Board> findAll(PageInfo pageInfo);

	List<Board> findAll(String writer, String title, String content);
}
