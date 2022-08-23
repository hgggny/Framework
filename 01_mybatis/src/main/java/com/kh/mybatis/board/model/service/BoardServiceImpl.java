package com.kh.mybatis.board.model.service;

import static com.kh.mybatis.common.template.SqlSessionTemplate.getSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.dao.BoardDaoImpl;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardServiceImpl implements BoardService {
	// 현재 클래스 정보를 넘겨준다. 
//	private static Logger log = LoggerFactory.getLogger(BoardServiceImpl.class); // 주석 처리하고 lombok으로 처리하기
	private BoardDao dao = new BoardDaoImpl();
	
	@Override
	public int getBoardCount() {
		int count = 0;
		SqlSession session = getSession(); 
		
		count = dao.getBoardCount(session);
		log.info("BoardCount : " + count);
		log.debug("BoardCount : " + count);
		
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
		log.debug("findAll(PageInfo pageInfo) - 호출");
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

	@Override
	public int save(Board board) {
		int result = 0;
		SqlSession session = getSession(); 
		
		if(board.getNo() != 0) {
			// update
			result = dao.updateBoard(session, board);
		} else {
			// insert
			result = dao.insertBoard(session, board);
		}
		
		if(result > 0) {
			// commit
			session.commit();
		} else {
			// rollback
			session.rollback();
		}
		
		session.close();
		
		return result;
	}

	@Override
	public int delete(int no) {
		int result = 0;
		SqlSession session = getSession(); 
		
		result = dao.updateStatus(session, no, BoardDao.STATUS_DISABLE);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}


}
