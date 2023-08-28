package com.bid.board.project.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.corperation.model.vo.CorpPagination;
import com.bid.board.corperation.model.vo.Corperation;
import com.bid.board.member.model.vo.Member;
import com.bid.board.member.model.vo.MemberPageNation;
import com.bid.board.project.model.dao.ProjectDAO;
import com.bid.board.project.model.vo.Project;
import com.bid.board.project.model.vo.ProjectPageNation;

@Service
public class ProjectServiceImple implements ProjectService {

	@Autowired
	private ProjectDAO dao;

	

	@Override
	public List<Corperation> getCorpName() {
		return dao.getCorpName();
	}

	@Override
	public List<Corperation> getOtherInfo(int corpNo) {
		System.out.println(corpNo+"============서비스");
		return dao.getOtherInfo(corpNo);
	}

	@Override
	public int insertNewProject(Project project) {
		return dao.insertNewProject(project);
	}

	@Override
	public Map<String, Object> getProjectList(int cp) {

		 int projectListCount = dao.projectListCount();

		    ProjectPageNation pagination = new ProjectPageNation(cp, projectListCount);
		    
		    
		    List<Project> projectList = dao.getProjectList(pagination);
		    
		    System.out.println(pagination);
		    
		    for (Project project : projectList) {
		        String projectAddr = project.getProjectAddr(); 
		        if (projectAddr != null) {
		        	projectAddr = projectAddr.replace(",,", " ");
		        	project.setProjectAddr(projectAddr);
		        }
		    }

		    Map<String, Object> getProjectList = new HashMap<String, Object>();
		    getProjectList.put("pagination", pagination);
		    getProjectList.put("projectList", projectList);

		
		return getProjectList;
	}

	@Override
	public Map<String, Object> selectProjectDeailValue(int projectNo) {
		
		List<Project> selectPDV = dao.selectProjectDetailValue(projectNo);
		
	    Map<String, Object> getProjectDetailValue = new HashMap<String, Object>();

		
		getProjectDetailValue.put("selectPDV", selectPDV);
		
		return getProjectDetailValue;
	}
	
	
}
