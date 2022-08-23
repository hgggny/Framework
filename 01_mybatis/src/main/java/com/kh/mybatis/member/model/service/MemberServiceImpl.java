package com.kh.mybatis.member.model.service;

import static com.kh.mybatis.common.template.SqlSessionTemplate.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.dao.MemberDaoImpl;
import com.kh.mybatis.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberServiceImpl implements MemberService {
	private MemberDao dao = new MemberDaoImpl();
	
	@Override
	public int getMemberCount() {
		log.info("getMemberCount() - 호출");
		int count = 0;
		SqlSession session = getSession();
		
//		count = new MemberDaoImpl().getMemberCount(session);
		count = dao.getMemberCount(session);
		
		session.close();
		
		return count;
	}

	@Override
	public List<Member> findAll() {
		log.debug("findAll() - 호출");
		List<Member> members = null;
		SqlSession session = getSession();

		members = dao.findAll(session);
		
		session.close();

		return members;
	}

	@Override
	public Member findMemberById(String id) {
		Member member = null;
		SqlSession session = getSession();

		member = dao.findMemberById(session, id);

		session.close();

		return member;
	}

	@Override
	public int save(Member member) {
		int result = 0;
		SqlSession session = getSession();

		if(member.getNo() != 0) {
			// update
			result = dao.updateMember(session, member);
		} else {
			// insert
			result = dao.insertMember(session, member);
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
		
		result = dao.delete(session, no);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		session.close();
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
