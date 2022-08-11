package com.kh.mybatis.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberDao {
	public int getMemberCount(SqlSession session);

	public List<Member> findAll(SqlSession session);

	public Member findMemberById(SqlSession session, String id);

	public int insertMember(SqlSession session, Member member);
}
