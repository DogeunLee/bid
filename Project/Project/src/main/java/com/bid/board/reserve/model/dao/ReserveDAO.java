package com.bid.board.reserve.model.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReserveDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
 


	public int deleteFromReserveTable(String id) {

		return sqlSession.delete("reserveMapper.deleteRMember",id);
		
	}

	public int insertToReserveTable(List<String> idsToAdd, int projectNo) {
		
		System.out.println("daoInsertToReserveMemberId"+idsToAdd);
		
		Map<String, Object> params = new HashMap<>();
		params.put("projectNo", projectNo);
		params.put("memberId", idsToAdd);
		return sqlSession.insert("reserveMapper.insertNewMember",params);
	}

	
	
	
}
