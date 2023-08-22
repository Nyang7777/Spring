package com.ict.bbs.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.bbs.model.service.bbsService;
import com.ict.bbs.model.vo.BVO;
import com.ict.bbs.model.vo.CVO;

// DB처리하는 메서드들을 가지고 있는 클래스
@Repository
public class bbsDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<BVO> getlist() {
		return sqlSessionTemplate.selectList("bbs.list");
	}

	public BVO getOneList(String b_idx) {
		return sqlSessionTemplate.selectOne("bbs.onelist", b_idx);
	}

	public int getHitUpdate(String b_idx) {
		return sqlSessionTemplate.update("bbs.hitup",b_idx);
	}

	public int getInert(BVO bvo) {
		return sqlSessionTemplate.insert("bbs.insert",bvo);
	}
	
	public int getTotalCount() {
		return sqlSessionTemplate.selectOne("bbs.count");
	}
	
	public List<BVO> getList(int offset, int limit){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("limit", limit);
		map.put("offset", offset);
		return sqlSessionTemplate.selectList("bbs.list",map);
	}
	
	public List<CVO> getCommList(String b_idx){
		return sqlSessionTemplate.selectList("bbs.comlist",b_idx);
	}
	
	public int getCommInsert(CVO cvo) {
		return sqlSessionTemplate.insert("bbs.cominsert",cvo);
	}
	
	public int getCommDelete(CVO cvo) {
		return sqlSessionTemplate.delete("bbs.comdelete",cvo);
	}
	
	public int getDelete(String b_idx) {
		return sqlSessionTemplate.update("bbs.delete",b_idx);
	}
	
	public int getUpdate(BVO bvo) {
		return sqlSessionTemplate.update("bbs.update",bvo);
	}
}


