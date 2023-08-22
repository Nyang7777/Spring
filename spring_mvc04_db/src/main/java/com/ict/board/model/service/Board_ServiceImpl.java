package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.model.dao.Board_DAO;
import com.ict.board.model.vo.Board_VO;

@Service
public class Board_ServiceImpl implements Board_Service {
	@Autowired
	private Board_DAO board_DAO;

	@Override
	public int getTotalCount() {
		return board_DAO.getTotalCount();
	}

	@Override
	public List<Board_VO> getList(int offset, int limit) {
		return board_DAO.getList(offset, limit);
	}
	
	@Override
	public int getInsert(Board_VO bv) {
		return board_DAO.getInsert(bv);
	}
	
	@Override
	public Board_VO getOneList(String idx) {
		return board_DAO.getOneList(idx);
	}

	@Override
	public int getBoardHit(String idx) {
		return board_DAO.getBoardHit(idx);
	}
	
	@Override
	public int getAnsInsert(Board_VO bv) {
		return board_DAO.getAnsInsert(bv);
	}
	
	@Override
	public int getLevupdate(Map<String, Integer> map) {
		return board_DAO.getLevupdate(map);
	}
	
	@Override
	public int getDelete(String idx) {
		return board_DAO.getDelete(idx);
	}
	
	@Override
	public int getUpdate(Board_VO bv) {
		return board_DAO.getUpdate(bv);
	}
}
