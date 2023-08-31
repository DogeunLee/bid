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

	public int insertNewMember(int projectNo, List<String> memberId) {
		Map<String, Object> params = new HashMap<>();
		params.put("projectNo", projectNo);
		params.put("memberId", memberId);
		return sqlSession.insert("reserveMapper.insertNewMember",params);
	}
	
	
	
	
}
