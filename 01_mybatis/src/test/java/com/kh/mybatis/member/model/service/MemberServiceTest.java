package com.kh.mybatis.member.model.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.member.model.vo.Member;

@DisplayName("MemberService 테스트")
@TestMethodOrder(OrderAnnotation.class)
class MemberServiceTest {
	private MemberService service;
	
	// 각각의 테스트 메소드들이 실행되기 전에 실행되는 메소드
	@BeforeEach
	void setUp( ) {
		service = new MemberServiceImpl();
	}
	
	@Test
	@Disabled
	// 가능한 환경인지 먼저 확인!
	void nothing() {
	}
	
	@Test
	@DisplayName("MemberService 객체 생성 테스트")
	@Disabled
	void create() {
		// 대상 객체가 not인지 테스트
		assertThat(service).isNotNull();
	}
	
	@Test
	@DisplayName("회원 수 조회 테스트")
	@Order(1)
	void getMeberCountTest() {
		int count = service.getMemberCount();
		
		// 양수인 경우
		// 메소드 체이닝 형식으로 추가할 수 있다. 
		assertThat(count).isPositive().isGreaterThanOrEqualTo(2);
//		assertThat(count).isGreaterThan(0);
	}
	
	@Test
	@DisplayName("모든 회원 조회 테스트")
	@Order(2)
	void findAllTest() {
		List<Member> members = null;
		
		members = service.findAll();
		
		assertThat(members.size()).isGreaterThan(0);
		assertThat(members.size()).isGreaterThanOrEqualTo(2);
		assertThat(members).isNotNull()
						   .isNotEmpty()
						   .extracting("id")
						   .isNotNull()
						   .contains("hgggny", "admin2");
	}
	
	@ParameterizedTest // 여러번 테스트를 돌리기 위한 어노테이션
	@ValueSource(strings = {"hgggny", "admin2"})
	@DisplayName("회원 조회 테스트(ID로 검색)")
	@Order(3)
	void findMemberByIdTest(String id) {
		Member member = null;
		
		member = service.findMemberById(id);
		
		assertThat(member).isNotNull()
						  .extracting("id")
						  .isEqualTo(id);
	}
	
	@ParameterizedTest
	@CsvSource({
		"test1, 1234, 홍길동",
		"test2, 4567, 임꺽정"
	})
	@DisplayName("회원 등록 테스트")
	@Order(4)
	void insertMemberTest(String id, String password, String name) {
		int result = 0;
		// 임의로 객체 만들어서 테스트하기
		Member member = new Member(id, password, name);
				
		result = service.save(member);	
		
		assertThat(result).isGreaterThan(0);
		assertThat(member.getNo()).isGreaterThan(0);
		assertThat(service.findMemberById(id)).isNotNull();
	}
	
	@ParameterizedTest
	@CsvSource({
		"test1, 0000, 이몽룡",
		"test2, 9999, 성춘향"
	})
	@DisplayName("회원 정보 수정 테스트")
	@Order(5)
	void updateMemberTest(String id, String password, String name) {
		int result = 0;
		Member member = null;
		
		member = service.findMemberById(id);
		
		member.setPassword(password);
		member.setName(name);
		
		result = service.save(member);
		
		// 실제로 DB에 Member가 수정되었는지 확인하기 위해서 다시 Member를 조회
		member = service.findMemberById(id);
		
		assertThat(result).isPositive().isGreaterThan(0);
		// 바뀐 이름이 name 과 같은지 테스트
		assertThat(member.getName()).isNotNull().isEqualTo(name);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"test1", "test2"})
	@DisplayName("회원 삭제 테스트")
	@Order(6)
	void deleteTest(String id) {
		int result = 0;
		Member member = null;
		
		member = service.findMemberById(id);
		result = service.delete(member.getNo());
		
		assertThat(result).isGreaterThan(0);
		assertThat(service.findMemberById(id)).isNull();

	}
	
	
	
	
	
	
	
	
	
	
}
