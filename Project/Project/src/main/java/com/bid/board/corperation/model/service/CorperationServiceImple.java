package com.bid.board.corperation.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.corperation.model.dao.CorperationDAO;
import com.bid.board.corperation.model.vo.CorpPagination;
import com.bid.board.corperation.model.vo.Corperation;
import com.bid.board.project.model.dao.ProjectDAO;

@Service
public class CorperationServiceImple implements CorperationService {
	
	
	@Autowired
	private CorperationDAO dao;
	
	
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
	public int updateInfo(Corperation corp) {
		return dao.updateInfo(corp);
	}
}
