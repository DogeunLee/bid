package com.bid.board.project.model.service;

import java.util.List;
import java.util.Map;

import com.bid.board.corperation.model.vo.Corperation;
import com.bid.board.project.model.vo.Project;

public interface ProjectService {

	List<Corperation> getCorpName();

	List<Corperation> getOtherInfo(int corpNo);

	int insertNewProject(Project project);

	Map<String, Object> getProjectList(int cp);

	Map<String, Object> selectProjectDeailValue(int projectNo);

}
