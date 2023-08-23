package com.bid.board.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.main.model.MemberPageNation;
import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Exp;
import com.bid.board.member.model.vo.Graduate;
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


	public int getMemberListCount() {
		return sqlSession.selectOne("memberMapper.selectMemberNo");
	}


	public List<Member> getMemberList(MemberPageNation pagenation) {

		int offset = (pagenation.getCurrentPage() - 1) * pagenation.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagenation.getLimit());

		return sqlSession.selectList("memberMapper.getMemberList",null,rowBounds);
	}


	public int memberIdDupCheck(String memberId) {
		return sqlSession.selectOne("memberMapper.memberIdDupCheck",memberId);
	}


	public int telDupCheck(String memberTel) {
		return sqlSession.selectOne("memberMapper.telDupCheck",memberTel);
	}


	public int emailDupCheck(String memberEmail) {
		return sqlSession.selectOne("memberMapper.emailDupCheck",memberEmail);
	}


	public int signUp(Member inputMember) {
		return sqlSession.insert("memberMapper.signUp",inputMember);
	}
	
	
	public int countSearchMemberList(int codeNo, String codeId, String memberName, String startDate, String endDate,
			String memberSt, String memberGender, String memberLv) {
		Map<String, Object> params = new HashMap<>();
		params.put("codeNo", codeNo);
		params.put("codeId", codeId);
		params.put("memberName", memberName);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("memberSt", memberSt);
		params.put("memberGender", memberGender);
		params.put("memberLv", memberLv);
		return sqlSession.selectOne("memberMapper.countSearchMemberList1",params);
	}
	
	public int countSearchMemberList(int codeNo, String codeId, String memberName) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("codeNo", codeNo);
		params.put("codeId", codeId);
		params.put("memberName", memberName);
		return sqlSession.selectOne("memberMapper.countSearchMemberList2",params);
	}



	public List<Member> searchMemberList(int codeNo, String codeId, String memberName, MemberPageNation pagenation) {

		int offset = (pagenation.getCurrentPage() - 1) * pagenation.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagenation.getLimit());
		
		Map<String, Object> params = new HashMap<>();
		params.put("codeNo", codeNo);
		params.put("codeId", codeId);
		params.put("memberName", memberName);
		return sqlSession.selectList("memberMapper.searchFormMemberList",params,rowBounds);

	}


	public List<Member> searchSelectList(int codeNo, String codeId, String memberName, String startDate, String endDate,
			String memberSt, String memberGender, String memberLv, MemberPageNation pagenation) {
		
		int offset = (pagenation.getCurrentPage() - 1) * pagenation.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagenation.getLimit());
		
		Map<String, Object> params = new HashMap<>();
		params.put("codeNo", codeNo);
		params.put("codeId", codeId);
		params.put("memberName", memberName);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("memberSt", memberSt);
		params.put("memberGender", memberGender);
		params.put("memberLv", memberLv);
		return sqlSession.selectList("memberMapper.searchSelectList",params, rowBounds);
	}


	public Member getMemberInfo(int memberNo) {

		return sqlSession.selectOne("memberMapper.getMemberInfo", memberNo);
	}


	public int updateInfos(Member inputMember) {
		return sqlSession.update("memberMapper.updateInfos", inputMember);
	}


	public int currentPassword(int memberNo, String currentPassword) {

		Map<String, Object> params = new HashMap<>();

		params.put("memberNo", memberNo);
		params.put("memberPw", currentPassword);

		return sqlSession.selectOne("memberMapper.checkCurrentPassword",params);
	}


	public int changePw(int memberNo, String newPassword) {

		Map<String, Object> params = new HashMap<>();

		params.put("memberNo", memberNo);
		params.put("memberPw", newPassword);

		return sqlSession.update("memberMapper.updateChangePw",params);
	}
	
	public int getNewMemberNo() {
		return sqlSession.selectOne("memberMapper.selectNewMemberNo");
	}


	public int setGarduateInfo(Graduate graduate) {

		return sqlSession.update("memberMapper.setGarduateInfo",graduate);
	}


	public int getExpInfo(Exp exp) {
		return sqlSession.update("memberMapper.getExpInfo",exp);
	}


	public int setCertiInfo(Certi certi) {
		
		return sqlSession.update("memberMapper.setCertiInfo",certi);
	}


	public List<Certi> selectCerti(int memberNo) {
		System.out.println(memberNo);
		return sqlSession.selectList("memberMapper.selectCerti",memberNo);
	}


	



	





}
