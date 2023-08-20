package com.bid.board.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.main.model.MemberPageNation;
import com.bid.board.member.model.dao.MemberDAO;
import com.bid.board.member.model.vo.Member;

@Service
public class MemberImple implements MemberService{

	@Autowired
	private MemberDAO dao;

	@Override
	public int getmemberNo() {
		return dao.getmemberNo();
	}

	@Override
	public Member login(Member inputMember) {


		Member loginMember = dao.login(inputMember);


		if(loginMember != null) {

			if( inputMember.getMemberPw().equals(loginMember.getMemberPw()) ){

				loginMember.setMemberPw(null); // 비교 끝났으면 비밀번호 지우기

			} else { // 비밀번호가 일치하지 않은 경우
				loginMember = null; // 로그인을 실패한 형태로 바꿔줌

			}
		}

		return loginMember;
	}



	@Override
	public Map<String, Object> getMemberList(int cp) {

	    int memberListCount = dao.getMmberListCount();

	    MemberPageNation pagination = new MemberPageNation(cp, memberListCount);

	    List<Member> memberList = dao.getMemberList(pagination);
	    
	    for (Member member : memberList) {
	        String memberAddr = member.getMemberAddr(); 
	        if (memberAddr != null) {
	            memberAddr = memberAddr.replace(",,", " ");
	            member.setMemberAddr(memberAddr);
	        }
	    }

	    Map<String, Object> getMemberList = new HashMap<String, Object>();
	    getMemberList.put("pagination", pagination);
	    getMemberList.put("memberList", memberList);

	    return getMemberList;
	}


	@Override
	public int memberIdDupCheck(String memberId) {
		return dao.memberIdDupCheck(memberId);
	}

	@Override
	public int telDupCheck(String memberTel) {
		return  dao.telDupCheck(memberTel);
	}

	@Override
	public int emailDupCheck(String memberEmail) {
		return dao.emailDupCheck(memberEmail);
	}

	@Override
	public int signUp(Member inputMember) {
		return dao.signUp(inputMember);
	}

	@Override
	public Map<String, Object> searchResultBack(int codeNo, String codeId, String memberName, String startDate,
			String endDate, String memberSt, String memberGender, String memberLv) {
		List<Member> searchMemberList = null;

		if ("all".equals(memberGender)) {
			memberGender = null;
		}
		if ("all".equals(memberLv)) {
			memberLv = null;
		}
		if ("all".equals(memberSt)) {
			memberSt = null;
		}

		if (codeNo == 7942) {

			searchMemberList = dao.searchSelectList(codeNo, codeId, memberName, startDate, endDate, memberSt, memberGender, memberLv);
		} else {
			searchMemberList = dao.searchMemberList(codeNo, codeId, memberName);
		}
		
		for (Member member : searchMemberList) {
	        String memberAddr = member.getMemberAddr(); 
	        if (memberAddr != null) {
	            memberAddr = memberAddr.replace(",,", " ");
	            member.setMemberAddr(memberAddr);
	        }
	    }

		Map<String, Object> searchResultBack = new HashMap<String, Object>();
		searchResultBack.put("searchMemberList", searchMemberList);

		return searchResultBack;
	}

	@Override
	public Member getMemberInfo(int memberNo) {
		
		
		Member getMemberInfoList = dao.getMemberInfo(memberNo);
		
	
		return getMemberInfoList;
	}

	@Override
	public int updateInfos(Member inputMember) {
		
		int updateInfos = dao.updateInfos(inputMember);

		
		return updateInfos;
	}

	@Override
	public int changePw(int memberNo, String currentPassword, String newPassword) {
		
		int current = dao.currentPassword(memberNo, currentPassword);
		
		int changePw = 0;
		
		if ( current == 0) {
			changePw = 0;
		} else {
			changePw =	dao.changePw(memberNo, newPassword);
		}
		
		return changePw;
	}




}
