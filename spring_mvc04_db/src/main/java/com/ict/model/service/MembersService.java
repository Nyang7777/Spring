package com.ict.model.service;

import java.util.List;

import com.ict.model.vo.MembersVO;

public interface MembersService {
	
	// 전체보기 
	List<MembersVO> membersList();
	
	// 넣기
	int memberAdd(MembersVO mvo);
	
	// 상세보기
	
	// 수정
	
	// 삭제
}
