package com.bid.board.project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.member.model.vo.Member;
import com.bid.board.member.model.vo.MemberPageNation;
import com.bid.board.project.model.dao.ProjectDAO;
import com.bid.board.project.model.vo.CorpPagination;
import com.bid.board.project.model.vo.Corperation;

@Service
public class ProjectServiceImple implements ProjectService {

	@Autowired
	private ProjectDAO dao;

	@Override
	public int inputNewCorp(Corperation corp) {
		return dao.inputNewCorp(corp);
	}

	@Override
	public Map<String, Object> getCorpList(int cp) {
		
		int corpListCount = dao.getCorpListCount();
		
		CorpPagination pagination = new CorpPagination(cp, corpListCount);
		
		List<Corperation> corpList = dao.getCorpList(pagination);
		
	    for (Corperation corp : corpList) {
        String corpAddr = corp.getCorpAddr(); 
        if (corpAddr != null) {
        	corpAddr = corpAddr.replace(",,", " ");
        	corp.setCorpAddr(corpAddr);
        }
    }
	    Map<String, Object> getCorpList = new HashMap<String, Object>();
	    getCorpList.put("pagination", pagination);
	    getCorpList.put("corpList", corpList);
	    
	    return getCorpList;

	}

	@Override
	public Corperation getCorpInfo(int corpNo) {
		return dao.getCorpInfo(corpNo);
	}

	@Override
	public List<Corperation> getCorpName() {
		return dao.getCorpName();
	}

	@Override
	public List<Corperation> getOtherInfo(int corpNo) {
		System.out.println(corpNo+"============서비스");
		return dao.getOtherInfo(corpNo);
	}
	
	
}
