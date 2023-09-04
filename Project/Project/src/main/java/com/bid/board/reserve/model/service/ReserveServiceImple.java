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
	    
	    // DB에서 현재 projectNo에 해당하는 memberId 리스트를 가져옵니다.
	    List<Member> currentMembers = mdao.selectProjectMemberList(projectNo, memberId);
	    
	    List<String> existingMemberIds = new ArrayList<>();
	    for (Member m : currentMembers) {
	        existingMemberIds.add(m.getMemberId());
	    }

	    // 삭제할 memberId
	    List<String> idsToDelete = new ArrayList<>(existingMemberIds);
	    for (String existingId : existingMemberIds) {
	        if (!memberId.contains(existingId)) {
	            idsToDelete.add(existingId);
	        }
	    }
	    for (String id : idsToDelete) {
	        dao.deleteFromReserveTable(id); // reserveTable에서 삭제
	        mdao.updateMemberSt(id); // memberSt 상태 변경
	    }

	    // 추가할 memberId
	    List<String> idsToAdd = new ArrayList<>();
	    for (String newId : memberId) {
	        if (!existingMemberIds.contains(newId)) {
	            idsToAdd.add(newId);
	        }
	    }
	    for (String id : idsToAdd) {
	        dao.insertToReserveTable(id, projectNo); // reserveTable에 추가
	        mdao.updateMemberSt(id); // memberSt 상태 변경
	    }

	    // 변경할 memberId
	    List<String> idsToUpdate = new ArrayList<>(memberId);
	    for (String id : idsToUpdate) {
	        if (existingMemberIds.contains(id)) {
	            dao.updateReserveTable(id, projectNo); // reserveTable에서 수정
	            mdao.updateMemberSt(id); // memberSt 상태 변경
	        }
	    }
	    
	    return 0;
	}

	
	
}
