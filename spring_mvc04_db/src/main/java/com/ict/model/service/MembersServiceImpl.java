package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.MembersDAO;
import com.ict.model.vo.MembersVO;

@Service
public class MembersServiceImpl implements MembersService {
	// DAO 호출
	@Autowired
	private MembersDAO membersDAO;
	
	public MembersDAO getMembersDAO() {
		return membersDAO;
	}

	public void setMembersDAO(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}

	@Override
	public List<MembersVO> membersList() {
		List<MembersVO> list = getMembersDAO().membersList();
		return list;
	}
	
	@Override
	public int memberAdd(MembersVO mvo) {
		int result = membersDAO.memberAdd(mvo);
		return result;
	}

	

}
