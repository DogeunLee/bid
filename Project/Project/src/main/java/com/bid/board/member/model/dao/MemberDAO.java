package com.bid.board.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getmemberNo() {
		return sqlSession.selectOne("memberMapper.selectMemberNo");
	}

}
