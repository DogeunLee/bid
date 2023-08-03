package com.bid.board.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.member.model.dao.MemberDAO;

@Service
public class MemberImple implements MemberService{

	@Autowired
	private MemberDAO dao;

	@Override
	public int getmemberNo() {
		return dao.getmemberNo();
	}
	
	
}
