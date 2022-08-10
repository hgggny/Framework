package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

public interface MemberDao {
	public int getMemberCount(SqlSession session);
}
