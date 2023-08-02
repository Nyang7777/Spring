package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.GuestBookDAO;
import com.ict.model.dao.GuestBookDAO2;
import com.ict.model.vo.GuestBookVO2;

@Service
public class GuestBookServiceImpl2 implements GuestBookService2 {
	
	// DAO가서 DB 결과 가져오기
	@Autowired
	private GuestBookDAO2 guestBookDAO2;
	
	public GuestBookDAO2 getGuestBookDAO() {
		return guestBookDAO2;
	}

	public void setGuestBookDAO2(GuestBookDAO2 guestBookDAO) {
		this.guestBookDAO2 = guestBookDAO;
	}

	@Override
	public List<GuestBookVO2> getGuestBookList() {
		return guestBookDAO2.getGuestBookList();
	}

	@Override
	public GuestBookVO2 getGuestBookOneList(String idx) {
		return guestBookDAO2.getGuestBookOneList(idx);
	}

	@Override
	public int getGuestBookInsert(GuestBookVO2 gvo) {
		return guestBookDAO2.getGuestBookInsert(gvo);
	}

	@Override
	public int getGuestBookUpdate(GuestBookVO2 gvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGeustBookDelete(String idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
