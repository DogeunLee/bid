package com.bid.board.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberGender;
	private String memberTel;
	private String memberBirth;
	private String memberHire;
	private String memberSt;
	private String memberLv;
	private String memberAddr;
	
}
