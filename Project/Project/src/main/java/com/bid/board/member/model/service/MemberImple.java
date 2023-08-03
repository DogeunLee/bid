package com.bid.board.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
