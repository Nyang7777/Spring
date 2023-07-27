package ex10_guest;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("guestdao")
public class DAO {
	// 실제 사용하는 클래스: SqlSessionTemplate
	@Autowired
	private static SqlSessionTemplate sessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sessionTemplate;
	}
 
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		DAO.sessionTemplate = sessionTemplate;
	}
	
	public List<VO> getList(){
		List<VO> list = sessionTemplate.selectList("guest.list");
		
		return list;
	}
}
