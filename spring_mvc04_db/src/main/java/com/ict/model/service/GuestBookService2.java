package com.ict.model.service;

import java.util.List;

import com.ict.model.vo.GuestBookVO;
import com.ict.model.vo.GuestBookVO2;

public interface GuestBookService2 {
		// 전체보기
		List<GuestBookVO2> getGuestBookList();
		
		// 상세보기
		GuestBookVO2 getGuestBookOneList(String idx);
		
		// 추가
		int getGuestBookInsert(GuestBookVO2 gvo);
		
		// 수정
		int getGuestBookUpdate(GuestBookVO2 gvo);
		
		// 삭제
		int getGeustBookDelete(String idx);
}
