package com.ict.transaction;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Repository
public class TxDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public int getInsert(TxVO txVO) throws Exception {
		// 트랜잭션을 사용하기 이전
//		int result = 0;
//		result += sqlSessionTemplate.insert("card.cardInsert",txVO);
//		result += sqlSessionTemplate.insert("card.ticketInsert",txVO);
//		return result;
		
		// 트랜잭션 사용
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.insert("card.cardInsert",txVO);
			System.out.println("결제 성공");
			result += sqlSessionTemplate.insert("card.ticketInsert",txVO);
			System.out.println("발권 성공");
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("결제 취소");
		}
		return result;
	}
}
