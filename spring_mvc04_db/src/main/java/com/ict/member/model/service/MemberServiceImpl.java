package com.ict.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.member.model.dao.MemberDAO;
import com.ict.member.model.vo.MemberVO;
import com.ict.model.vo.MembersVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private  MemberDAO memberDAO;

	@Override
	public int getMemberAdd(MembersVO m2vo) {
		return memberDAO.getMemberAdd(m2vo);
	}
	
	@Override
	public MemberVO getMemberPwd(String m_id) {
		return memberDAO.getMemberPwd(m_id);
	}
	
}
