package com.bid.board.corperation.model.service;

import java.util.Map;

import com.bid.board.corperation.model.vo.Corperation;

public interface CorperationService {

	int inputNewCorp(Corperation corp);

	Map<String, Object> getCorpList(int cp);

	Corperation getCorpInfo(int corpNo);

	int updateInfo(Corperation corp);
	
}
