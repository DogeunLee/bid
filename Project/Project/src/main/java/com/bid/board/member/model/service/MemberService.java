package com.bid.board.member.model.service;

import com.bid.board.member.model.vo.Member;

public interface MemberService {

	int getmemberNo();

	Member login(Member inputMember);
	
}
