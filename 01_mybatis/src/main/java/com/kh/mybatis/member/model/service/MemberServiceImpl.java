package com.kh.mybatis.member.model.service;

import static com.kh.mybatis.common.template.SqlSessionTemplate.*;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	@Override
	public int getMemberCount() {
		int count = 0;
		SqlSession session = getSession();
		
		count = new MemberDaoImpl().getMemberCount(session);
		
		session.close();
		
		return count;
	}
}
