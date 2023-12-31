package com.bid.board.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Exp;
import com.bid.board.member.model.vo.Graduate;
import com.bid.board.member.model.vo.Member;
import com.bid.board.member.model.vo.MemberPageNation;

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
		System.out.println(inputMember);
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

		return sqlSession.insert("memberMapper.setGarduateInfo",graduate);
	}


	public int getExpInfo(Exp exp) {
		return sqlSession.insert("memberMapper.getExpInfo",exp);
	}


	public int setCertiInfo(Certi certi) {
		
		return sqlSession.insert("memberMapper.setCertiInfo",certi);
	}


	public List<Certi> selectCerti(int memberNo) {
		System.out.println(memberNo);
		return sqlSession.selectList("memberMapper.selectCerti",memberNo);
	}


	public int updateGraduate(Graduate graduate, int memberNo) {
	
		System.out.println("DAO's graduate update value is = " + graduate);
	    int totalUpdated = 0;
	    Map<String, Object> singleGrad = new HashMap<>();
	    
	    singleGrad.put("memberNo", memberNo);
	    singleGrad.put("graduate", graduate);
	    
	    
		if ( graduate.getGradNo() == 0) {
			totalUpdated += sqlSession.insert("memberMapper.setGarduateInfo",singleGrad);
		} else {
			totalUpdated += sqlSession.update("memberMapper.updateGraduate",graduate);
		}
		
		return totalUpdated;
	}

	public int updateExp(Exp exp, int memberNo) {
		System.out.println("DAO's exp update value is = " + exp);
		
	    int totalUpdated = 0;
	    Map<String, Object> singleExp = new HashMap<>();
	    
	    singleExp.put("memberNo", memberNo);
	    singleExp.put("graduate", exp);
	    
	    
		if ( exp.getExpNo() == 0) {
			totalUpdated += sqlSession.insert("memberMapper.getExpInfo",singleExp);
		} else {
			totalUpdated += sqlSession.update("memberMapper.updatExp",exp);
		}
		
		return totalUpdated; 
	}


	public int updateCerti(Map<String, Object> certi, int memberNo) {
	    List<Integer> certiNos = (List<Integer>) certi.get("certiNo");
	    List<String> certiDates = (List<String>) certi.get("certiDate");
	    List<String> certiNames = (List<String>) certi.get("certiName");
	    
	    int totalUpdated = 0;
	    
	    for (int i = 0; i < certiNos.size(); i++) {
	        Map<String, Object> singleCerti = new HashMap<>();
	        singleCerti.put("certiNo", certiNos.get(i));
	        singleCerti.put("certiDate", certiDates.get(i));
	        singleCerti.put("certiName", certiNames.get(i));
	        singleCerti.put("memberNo", memberNo);
	        
	        if (certiNos.get(i) != null) {
	            totalUpdated += sqlSession.update("memberMapper.updateCerti", singleCerti);
	        }
	        else {
	            totalUpdated += sqlSession.insert("memberMapper.insertCerti", singleCerti);
	        }

	       
	    }
	    
	    return totalUpdated;
	}

	public List<Member> selectProjectMemberList(int projectNo) {
		return sqlSession.selectList("memberMapper.selectProjectMemberList",projectNo);
	}
//	
//
//	public int updateMemberSt(List<String> memberId) {
//		Map<String, Object> params = new HashMap<>();
//		params.put("memberId", memberId);
//		return sqlSession.update("memberMapper.updateSt", params);
//	}
//
//	//	public List<Member> selectPMemberList(int projectNo, List<String> memberId) {
//	Map<String, Object> params = new HashMap<>();
//	params.put("projectNo", projectNo);
//	params.put("memberId", memberId);
//	return sqlSession.selectList("memberMapper.selectProjectMemberList", params);
//}
//
//

	// 현재 선택되어있는 멤버 리스트 조회
	public List<Member> selectReservePMemberList(int projectNo) {
		
		System.out.println("선택되어있는 멤버 리스트 조회 프로젝트넘버"+projectNo);
		
		
		 Map<String, Object> params = new HashMap<>();
		 
		 params.put("projectNo", projectNo);
		 
		return sqlSession.selectList("memberMapper.selectReservePMemberList",params);
	}


	public int setMemberStN(List<String> id) {
		
		System.out.println("SetMemberSt를 N으로바꿀 STAT!"+id);
		
		return  sqlSession.update("memberMapper.setMemberStN",id);
		
	}


	public int setMemberStY(List<String> idsToAdd) {
		
		System.out.println("SetMemberSt를 N으로바꿀 (STAT2)아이디는!"+idsToAdd);
		
		return sqlSession.update("memberMapper.setMemberStY",idsToAdd);
	}
	
	







	



	





}
