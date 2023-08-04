package com.bid.board.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.main.model.MemberPageNation;
import com.bid.board.member.model.vo.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int getmemberNo() {
		return sqlSession.selectOne("memberMapper.selectMemberNo");
	}


	//Login
	public Member login(Member inputMember) {
		return sqlSession.selectOne("memberMapper.login", inputMember);
	}
	

	public int getMmberListCount() {
		return sqlSession.selectOne("memberMapper.selectMemberNo");
	}


	public List<Member> getMemberList(MemberPageNation pagenation) {
		
		int offset = (pagenation.getCurrentPage() - 1) * pagenation.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagenation.getLimit());
		
		return sqlSession.selectList("memberMapper.getMemberList",rowBounds);
	}



}
