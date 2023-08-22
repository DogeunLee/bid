package com.bid.board.member.model.service;

import java.util.Map;

import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Exp;
import com.bid.board.member.model.vo.Graduate;
import com.bid.board.member.model.vo.Member;

public interface MemberService {

	int getmemberNo();

	Member login(Member inputMember);

	Map<String, Object> getMemberList(int cp);

	int memberIdDupCheck(String memberId);

	int telDupCheck(String memberTel);

	int emailDupCheck(String memberEmail);

	int signUp(Member inputMember, Graduate graduate, Exp exp, Certi certi);

	Map<String, Object> searchResultBack(int codeNo, String codeId, String memberName, String startDate, String endDate,
			String memberSt, String memberGender, String memberLv, int cp);

	Member getMemberInfo(int memberNo);

	int updateInfos(Member inputMember);

	int changePw(int memberNo, String currentPassword, String newPassword);
	
}
