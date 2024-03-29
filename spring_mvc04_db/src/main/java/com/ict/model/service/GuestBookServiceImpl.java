package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.GuestBookDAO;
import com.ict.model.vo.GuestBookVO;

@Service
public class GuestBookServiceImpl implements GuestBookService {
	
	// DAO가서 DB 결과 가져오기
	@Autowired
	private GuestBookDAO guestBookDAO;
	
	public GuestBookDAO getGuestBookDAO() {
		return guestBookDAO;
	}

	public void setGuestBookDAO(GuestBookDAO guestBookDAO) {
		this.guestBookDAO = guestBookDAO;
	}

	@Override
	public List<GuestBookVO> getGuestBookList() {
		return guestBookDAO.getGuestBookList();
	}

	@Override
	public GuestBookVO getGuestBookOneList(String idx) {
		return guestBookDAO.getGuestBookOneList(idx);
	}

	@Override
	public int getGuestBookInsert(GuestBookVO gvo) {
		return guestBookDAO.getGuestBookInsert(gvo);
	}

	@Override
	public int getGuestBookUpdate(GuestBookVO gvo) {
		return guestBookDAO.getGuestBookUpdate(gvo);
	}

	@Override
	public int getGeustBookDelete(String idx) {
		return guestBookDAO.getGuestBookDelete(idx);
	}
	
}
