package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	public static SqlSession getSession() {
		SqlSession session = null;
		InputStream is = null;
		SqlSessionFactoryBuilder builder = null;
		SqlSessionFactory factory = null;
		
		try {
			// SqlSession 객체 생성하기
			
			// 1. SqlSessionFactoryBuilder 객체 생성
			builder = new SqlSessionFactoryBuilder();
			// mybatis에서 자원을 좀 더 쉽게 로드할 수 있는 Resources 클래스를 제공한다.
			// 클래스패스를 기준으로 나타내기 때문에 target 바로 아래에 있는 파일명으로 적는다.
			is = Resources.getResourceAsStream("mybatis-config.xml");
			
			// 2. SqlSessionFactory 객체 생성
			// builder.build(InputStream 변수, environment);
			factory = builder.build(is);
			// 다른걸로 연결하고 싶으면 environment 를 따로 설정한다. 따로 설정하지 않으면 기본값(=web) ! 
			// factory = builder.build(is, "kh"); 
			
			// 3. SqlSession 객체 생성
			// factory.openSession(autocommit 여부 - 기본값 : false);
			// true: 오토 commit, false: 수동 commit
			session = factory.openSession(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
}
