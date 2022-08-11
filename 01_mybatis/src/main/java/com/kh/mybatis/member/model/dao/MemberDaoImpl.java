package com.kh.mybatis.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDaoImpl implements MemberDao {

	public int getMemberCount(SqlSession session) {
 
		return session.selectOne("memberMapper.selectCount");
	}

	@Override
	public List<Member> findAll(SqlSession session) {

		return session.selectList("memberMapper.selectAll");
	}

	@Override
	public Member findMemberById(SqlSession session, String id) {

		return session.selectOne("memberMapper.selectMemberById", id);
	}

	@Override
	public int insertMember(SqlSession session, Member member) {

		return session.insert("memberMapper.insertMember", member);
	}
}
