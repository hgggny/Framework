package com.kh.mvc.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mvc.member.model.mapper.MemberMapper;
import com.kh.mvc.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	// mapperscanner가 자동으로 구현해준다. 
//	@Autowired
//	private MemberDao dao;
	
//	@Autowired
//	private SqlSession session;
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public Member login(String id, String password) {
		Member member = null;
		
//		member = dao.findMemberById(session, id);
		member = mapper.selectMemberById(id);

		if(member != null && member.getPassword().equals(password)) {
			return member;
		} else {
			return null;
		}
	}

}
