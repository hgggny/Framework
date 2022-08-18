package com.kh.mybatis.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

public class BoardDaoImpl implements BoardDao {

	@Override
	public int getBoardCount(SqlSession session) {
		
		return session.selectOne("boardMapper.selectBoardCount");
	}
	
	@Override
	public int getBoardCount(SqlSession session, String writer, String title, String content) {
		Map<String, String> map = new HashMap<>();
		
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		
		return session.selectOne("boardMapper.selectBoardCountByKeyWord", map);
	}
	
	@Override
	public int getBoardCount(SqlSession session, String[] filters) {
		Map<String, String[]> map = new HashMap<>();
		
		map.put("filters", filters);
		
		return session.selectOne("boardMapper.selectBoardCountByFilters", map);
	}
	
	@Override
	public List<Board> findAll(SqlSession session, PageInfo pageInfo) {
		/*
		 * mybatis에서 페이징 처리
		 * 
		 *  mybatis에서는 페이징 처리를 위해서 RowBounds 라는 클래스를 제공해 준다.
		 *  RowBounds의 객체를 생성할 때 offset과 limit 값을 전달해서 페이징 처리를 쉽게 구현한다.
		 *  (offset 만큼 건너뛰고 limit 만큼 가져온다.)
		 *  
		 *  offset = 0, limit = 10
		 *  	- 0개를 건너뛰고 10개를 가져온다. (1 ~ 10)
		 *  
		 *  offset = 10, limit = 10
		 *  	- 10개를 건너뛰고 10개를 가져온다. (11 ~ 20)
		 *  
		 *  offset = 20, limit = 10
		 *  	- 20개를 건너뛰고 10개를 가져온다. (21 ~ 30)
		 */
		int offset = (pageInfo.getCurrentPage() - 1) * pageInfo .getListLimit();
		int limit = pageInfo .getListLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return session.selectList("boardMapper.selectAll", null, rowBounds);
	}

	@Override
	public List<Board> findAll(SqlSession session, PageInfo pageInfo, String writer, String title, String content) {
		Map<String, String> map = new HashMap<>();
		int offset = (pageInfo.getCurrentPage() - 1) * pageInfo .getListLimit();
		int limit = pageInfo .getListLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);
		
		return session.selectList("boardMapper.selectBoardListByKeyWord", map, rowBounds);
	}

	@Override
	public List<Board> findAll(SqlSession session, PageInfo pageInfo, String[] filters) {
		/*
		 * List 타입이나 Array 타입의 데이터를 파라미터로 전달하면 
		 * Mapper.xml에서는 list, array라는 이름으로 파라미터에 접근해야 한다. 
		 * 
		 * 만약에 filters 라는 이름을 Mappter.xml에서 사용하고 싶으면 
		 * Map 객체에 담아서 파라미터에 전달한다. 
		 */
		
		Map<String, String[]> map = new HashMap<>();
		int offset = (pageInfo.getCurrentPage() - 1) * pageInfo .getListLimit();
		int limit = pageInfo.getListLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);

		map.put("filters", filters);
		
//		return session.selectList("boardMapper.selectBoardListByFilters", filters);
//		return session.selectList("boardMapper.selectBoardListByFilters", Arrays.asList(filters));
		return session.selectList("boardMapper.selectBoardListByFilters", map, rowBounds);
	}

	@Override
	public Board findBoardByNo(SqlSession session, int no) {
		
		return session.selectOne("boardMapper.selectBoardByNo", no);
	}

	@Override
	public int insertBoard(SqlSession session, Board board) {

		return session.insert("boardMapper.insertBoard", board);
	}

	@Override
	public int updateBoard(SqlSession session, Board board) {

		return session.update("boardMapper.updateBoard", board);
	}
	
	@Override
	public int updateStatus(SqlSession session, int no, String status) {
		// Object - String이랑 int를 둘다 받아야 하므로 상위 객체로 받는다
		Map<String, Object> map = new HashMap<>();
		
		map.put("no", no);
		map.put("status", status);
		
		return session.update("boardMapper.updateStatus", map);
	}













	
	
	
	
	
	
	
	
	
}
