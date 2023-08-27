package com.bid.board.project.model.service;

import java.util.List;
import java.util.Map;

import com.bid.board.project.model.vo.Corperation;

public interface ProjectService {

	int inputNewCorp(Corperation corp);

	Map<String, Object> getCorpList(int cp);

	Corperation getCorpInfo(int corpNo);

	List<Corperation> getCorpName();

	List<Corperation> getOtherInfo(int corpNo);

}
