package com.bid.board.reserve.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.member.model.dao.MemberDAO;
import com.bid.board.reserve.model.dao.ReserveDAO;

@Service
public class ReserveServiceImple implements ReserveService {

	@Autowired
	private ReserveDAO dao;
	
	@Autowired
	private MemberDAO mdao;

	@Override
	public int insertNewMember(int projectNo, List<String> memberId) {
		
		
		int result =  dao.insertNewMember(projectNo,memberId);
		
		// 만약 memberId가 selectMemberId의 값과 다르다면
		
		
		System.out.println(result+"프로젝트의 삽입결과를 보여주세요!");
		
		if ( result > 0 ) {
			
			int updateSt = mdao.updateMemberSt(memberId);
			
		}
		
		
		return result;
	}
	
}
