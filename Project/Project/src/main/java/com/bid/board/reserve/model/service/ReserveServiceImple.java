package com.bid.board.reserve.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.member.model.dao.MemberDAO;
import com.bid.board.member.model.vo.Member;
import com.bid.board.reserve.model.dao.ReserveDAO;

@Service
public class ReserveServiceImple implements ReserveService {

	@Autowired
	private ReserveDAO dao;
	
	@Autowired
	private MemberDAO mdao;

//	@Override
//	public int insertNewMember(int projectNo, List<String> memberId) {
//		
//		// 만약 memberId가 selectMemberId의 값과 다르다면
//		List<Member> memberList = mdao.selectPMemberList(projectNo, memberId);
//		
//		int result =  dao.insertNewMember(projectNo,memberId);
//		
//		System.out.println(result+"프로젝트의 삽입결과를 보여주세요!");
//		
//		if ( result > 0 ) {
//			
//			int updateSt = mdao.updateMemberSt(memberId);
//			
//		}
//		
//		
//		return result;
//	}
	
	@Override
	public int insertNewMember(int projectNo, List<String> memberId) {
	    
	    List<Member> currentMembers = mdao.selectReservePMemberList(projectNo);
	    List<String> existingMemberIds = new ArrayList<>();
	    for (Member m : currentMembers) {
	        existingMemberIds.add(m.getMemberId());
	    }

	    List<String> idsToDelete = new ArrayList<>();

	    // 만약 memberId가 null이거나 비어있으면 모든 existingMemberIds를 삭제
	    if (memberId == null || memberId.isEmpty()) {
	        idsToDelete.addAll(existingMemberIds);
	    } else {
	        for (String existingId : existingMemberIds) {
	            if (!memberId.contains(existingId)) {
	                idsToDelete.add(existingId);
	            }
	        }
	    }
	    for (String id : idsToDelete) {
	        dao.deleteFromReserveTable(id); // reserveTable에서 삭제
	        mdao.setMemberStN(idsToDelete); // reserveTable에서 삭제
	    }
	    
	    // 추가할 memberId
	    List<String> idsToAdd = new ArrayList<>();
	    if (memberId != null) {
	        for (String newId : memberId) {
	            if (!existingMemberIds.contains(newId)) {
	                idsToAdd.add(newId);
	            }
	        }
	    }

	    // reserveTable에 모든 idsToAdd 항목을 한 번의 호출로 추가
	    if (!idsToAdd.isEmpty()) {
	        dao.insertToReserveTable(idsToAdd, projectNo);
	        mdao.setMemberStY(idsToAdd); // reserveTable에서 삭제
	    }

	    return 0;
	}


	
	
}
