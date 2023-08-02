package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestBookVO;
import com.ict.model.vo.GuestBookVO2;

@Repository
public class GuestBookDAO2 {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 서비스에서 DB처리하는 메서드를 모두 받아줘야 한다
	// 리스트
	public List<GuestBookVO2> getGuestBookList(){
		return sqlSessionTemplate.selectList("guestbook2.list");
	}
	
	// 추가 
	public int getGuestBookInsert(GuestBookVO2 gvo) { 
		return sqlSessionTemplate.insert("guestbook2.insert",gvo); }
	
	// 상세보기 
	public GuestBookVO2 getGuestBookOneList(String idx) { 
		return	sqlSessionTemplate.selectOne("guestbook2.onelist",idx); }
	
	// 삭제 
	public int getGuestBookDelete(String idx) { 
		return	sqlSessionTemplate.delete("guestbook2.delete",idx); }
	
	// 수정 
	public int getGuestBookUpdate(GuestBookVO2 gvo) { 
		return	sqlSessionTemplate.update("guestbook2.update",gvo); }
	
}
