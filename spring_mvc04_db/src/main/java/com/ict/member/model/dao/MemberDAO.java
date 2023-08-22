package com.ict.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.member.model.vo.MemberVO;
import com.ict.model.vo.MembersVO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;
	
	public int getMemberAdd(MembersVO m2vo) {
		return sqlsessionTemplate.insert("member.insert", m2vo);
	}
	
	public MemberVO getMemberPwd(String m_id) {
		return sqlsessionTemplate.selectOne("member.selectPwd", m_id);
	}
}
