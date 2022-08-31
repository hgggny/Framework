package com.kh.mvc.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.mvc.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	Member selectMemberById(@Param("id") String id);

	int insertMember(Member member);
}
