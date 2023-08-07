package com.bid.board.member.model.service;

import java.util.Map;

import com.bid.board.member.model.vo.Member;

public interface MemberService {

	int getmemberNo();

	Member login(Member inputMember);

	Map<String, Object> getMemberList(int cp);

	int memberIdDupCheck(String memberId);
	
}
