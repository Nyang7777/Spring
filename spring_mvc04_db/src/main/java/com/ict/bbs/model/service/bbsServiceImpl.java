package com.ict.bbs.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.bbs.model.dao.bbsDAO;
import com.ict.bbs.model.vo.BVO;
import com.ict.bbs.model.vo.CVO;

@Service
public class bbsServiceImpl implements bbsService{
	
	@Autowired
	private bbsDAO bbsDAO;

	@Override
	public List<BVO> getlist() {
		return bbsDAO.getlist();
	}

	@Override
	public BVO getOneList(String b_idx) {
		return bbsDAO.getOneList(b_idx);
	}

	@Override
	public int getHitUpdate(String b_idx) {
		return bbsDAO.getHitUpdate(b_idx);
	}

	@Override
	public int getInert(BVO bvo) {
		return bbsDAO.getInert(bvo);
	}

	@Override
	public int getTotalCount() {
		return bbsDAO.getTotalCount();
	}

	@Override
	public List<BVO> getlist(int offset, int limit) {
		return bbsDAO.getList(offset,limit);
	}

	@Override
	public List<CVO> getCommentList(String b_idx) {
		return bbsDAO.getCommList(b_idx);
	}
	
	@Override
	public int getCommInsert(CVO cvo) {
		return bbsDAO.getCommInsert(cvo);
	}
	
	@Override
	public int getCommDelete(CVO cvo) {
		return bbsDAO.getCommDelete(cvo);
	}
	
	@Override
	public int getDelete(String b_idx) {
		return bbsDAO.getDelete(b_idx);
	}
	
	@Override
	public int getUpdate(BVO bvo) {
		return bbsDAO.getUpdate(bvo);
	}

}
