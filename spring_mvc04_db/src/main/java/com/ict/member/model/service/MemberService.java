package com.ict.member.model.service;

import com.ict.member.model.vo.MemberVO;
import com.ict.model.vo.MembersVO;

public interface MemberService {
	// 회원가입
	int getMemberAdd(MembersVO m2vo);
	
	// 로그인
	// 1. 아이디로 패스워드 일치하는지 검사
	MemberVO getMemberPwd(String m_id);
	
	// 아이디 찾기
	
	// 비밀번호 찾기
	
}
