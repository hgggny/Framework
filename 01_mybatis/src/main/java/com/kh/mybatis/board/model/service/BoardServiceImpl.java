package com.kh.mybatis.board.model.service;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.dao.BoardDaoImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public class BoardServiceImpl implements BoardService {
	private BoardDao dao = new BoardDaoImpl();
	
	@Override
	public int getBoardCount() {
		int count = 0;
		SqlSession session = getSession(); 
		
		count = dao.getBoardCount(session);
		
		session.close();
		
		return count;
	}
	
	@Override
	public int getBoardCount(String writer, String title, String content) {
		int count = 0;
		SqlSession session = getSession(); 

		count = dao.getBoardCount(session, writer, title, content);
		
		session.close();
		
		return count;
	}
	
	@Override
	public int getBoardCount(String[] filters) {
		int count = 0;
		SqlSession session = getSession(); 
		
		count = dao.getBoardCount(session, filters);
		
		session.close();

		return count;
	}
	
	@Override
	public List<Board> findAll(PageInfo pageInfo) {
		List<Board> list = null;
		SqlSession session = getSession(); 
		
		list = dao.findAll(session, pageInfo);
		
		session.close();
		
		return list;
	}

	@Override
	public List<Board> findAll(PageInfo pageInfo, String writer, String title, String content) {
		List<Board> list = null;
		SqlSession session = getSession(); 
		
		list = dao.findAll(session, pageInfo, writer, title, content);
		
		session.close();

		return list;
	}

	@Override
	public List<Board> findAll(PageInfo pageInfo, String[] filters) {
		List<Board> list = null;
		SqlSession session = getSession(); 
		
		list = dao.findAll(session, pageInfo, filters);
				
		session.close();

		return list;
	}

	@Override
	public Board findBoardByNo(int no) {
		Board board = null;
		SqlSession session = getSession(); 
		
		board = dao.findBoardByNo(session, no);

		session.close();

		return board;
	}


}
