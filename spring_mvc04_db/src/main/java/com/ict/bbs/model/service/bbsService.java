package com.ict.bbs.model.service;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ict.bbs.model.vo.BVO;
import com.ict.bbs.model.vo.CVO;

public interface bbsService {
	
	// 전체 게시물
	public int getTotalCount();
	
	// 페이지를 위한 리스트
	public List<BVO> getlist(int offset, int limit);
	
	// 리스트
	public List<BVO> getlist();
	
	// 상세보기
	public BVO getOneList(String b_idx);
	
	// 조회수 업데이트
	public int getHitUpdate(String b_idx);
	
	// 글쓰기
	public int getInert(BVO bvo);
	
	// 댓글 가져오기
	public List<CVO> getCommentList(String b_idx);
	
	// 댓글 쓰기
	public int getCommInsert(CVO cvo);
	
	// 댓글 삭제
	public int getCommDelete(CVO cvo);
	
	// 글 삭제
	public int getDelete(String b_idx);
	
	// 글 수정
	public int getUpdate(BVO bvo);
}
