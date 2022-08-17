package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

import net.bytebuddy.asm.Advice.Argument;

@TestMethodOrder(OrderAnnotation.class)
class BoardServiceTest {
	private BoardService service = null;
	
	@BeforeEach
	void setUp() {
		service = new BoardServiceImpl();
	}
	
	@Test
	@Disabled
	void nothing() {

	}
	
	@Test
	@Disabled
	void create() {
		assertThat(service).isNotNull();
	}

	@Test
	@Order(1)
	@DisplayName("게시글 수 조회(전체 목록) 테스트")
	void getBoardCountTest() {
		int count = 0;
		
		count = service.getBoardCount();
		
//		System.out.println(count);
		
		assertThat(count).isGreaterThan(0);
	}
	
	@ParameterizedTest
	@CsvSource({
		"1, 10",
		"16, 5"
	})
	@Order(2)
	@DisplayName("게시물 목록 조회(전체 목록) 테스트")
	void findAllTest(int currentPage, int expected) {
		List<Board> list = null;
		PageInfo pageInfo = null;
		int listCount = 0;
		
//		pageInfo = new PageInfo(1, 10, 158, 10);
//		pageInfo = new PageInfo(16, 10, 158, 10);
//		pageInfo = new PageInfo(currentPage, 10, 158, 10);
		
		listCount = service.getBoardCount();
		pageInfo = new PageInfo(currentPage, 10, listCount, 10);
				
		list = service.findAll(pageInfo);
		
//		System.out.println(list);
//		System.out.println(list.size());
		
		// isNotNull - NULL 인지 아닌지
		// isNotEmpty - list 객체의 요소가 비어있는가
		assertThat(list).isNotNull().isNotEmpty();
		assertThat(list.size()).isEqualTo(expected);
	}
	
	@ParameterizedTest
	@CsvSource(
		value = {
				"null, null, null",
				"ad, null, null",
				"null, 50, null",
				"null, null, 13",
		},
		nullValues = "null"
	)
	@Order(3)
	@DisplayName("게시글 수 조회(검색 기능 적용) 테스트")
	void getBoardCountTest(String writer, String title, String content) {
		int count = 0;
		
		count = service.getBoardCount(writer, title, content);
		
		assertThat(count).isGreaterThan(0);
	}
	
	@ParameterizedTest
	@CsvSource(
		value = {
				"null, null, null, 10",
				"ad, null, null, 3",
				"null, 50, null, 2",
				"null, null, 13, 10",
		},
		// null값으로 변경되어 들어갈 수 있게 설정. 
		nullValues = "null"
	)
	@Order(4)
	@DisplayName("게시물 목록 조회(검색 기능 적용) 테스트")
	void findAllTest(String writer, String title, String content, int expected) {
		List<Board> list = null;
		PageInfo pageInfo = null;
		int listCount = 0;
		
		listCount = service.getBoardCount(writer, title, content);
		pageInfo = new PageInfo(1, 10, expected, 10);
		list = service.findAll(pageInfo, writer, title, content);
		
//		System.out.println(list);
//		System.out.println(list.size());
		
		assertThat(list).isNotNull().isNotEmpty();
		assertThat(list.size()).isEqualTo(expected);
	}
	
	@ParameterizedTest
	@MethodSource("filterProvider")
	@Order(5)
	@DisplayName("게시글 수 조회(필터 기능 적용) 테스트")
	void getBoardCount() {
		int count = 0;
		String [] filters = {"B2", "B3"};
		
		count = service.getBoardCount(filters);
		
		assertThat(count).isGreaterThan(0);
	}
	
	@ParameterizedTest
	@MethodSource("filterProvider")
	@Order(6)
	@DisplayName("게시글 목록 조회(필터 기능 적용) 테스트")
	void findAllTest(String[] filters) {
		// String [] filters = request.getParameterValues("filter")
//		String [] filters = {"B2", "B3"};
		List<Board> list = null;
		PageInfo pageInfo = null;
		int listCount = 0;
		
		listCount = service.getBoardCount(filters);
		pageInfo = new PageInfo(1, 10, listCount, 10);
		list = service.findAll(pageInfo, filters);
		
//		System.out.println(list);
//		System.out.println(list.size());
		
		// 조회되는게 없어도 null은 아니고 빈 배열이 나온다. 
		assertThat(list).isNotNull().isNotEmpty();
	}
	
	@Test
	@Order(7)
	@DisplayName("게시글 상세 조회(댓글 포함) 테스트")
	void findBoardByNoTest() {
		Board board = null;
		
		board = service.findBoardByNo(122);
		
		System.out.println(board);
		System.out.println(board.getReplies());
		System.out.println(board.getReplies().size());
		
		assertThat(board).isNotNull();
	}
	
	
	
	
	
	
	
	
	
	public static Stream<Arguments> filterProvider() {
		return Stream.of(
			Arguments.arguments((Object) new String[] {"B2", "B3"}),
			Arguments.arguments((Object) new String[] {"B3"})
		);
	}
	
	
}
