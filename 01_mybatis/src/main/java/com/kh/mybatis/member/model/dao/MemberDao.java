package com.kh.mybatis.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberDao {
	int getMemberCount(SqlSession session);

	List<Member> findAll(SqlSession session);

	Member findMemberById(SqlSession session, String id);

	int insertMember(SqlSession session, Member member);

	int updateMember(SqlSession session, Member member);
	
	int delete(SqlSession session, int no);

}
