package com.bid.board.project.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.corperation.model.vo.CorpPagination;
import com.bid.board.corperation.model.vo.Corperation;
import com.bid.board.project.model.vo.Project;
import com.bid.board.project.model.vo.ProjectPageNation;

@Repository
public class ProjectDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;



	public List<Corperation> getCorpName() {
		return sqlSession.selectList("corpMapper.getCorpName");
	}

	public List<Corperation> getOtherInfo(int corpNo) {
		System.out.println(corpNo+"============DAO");
		return sqlSession.selectList("corpMapper.getOtherInfo", corpNo);
	}

	public int insertNewProject(Project project) {
		return sqlSession.insert("projectMapper.insertNewProject", project);
	}

	public int projectListCount() {
		return sqlSession.selectOne("projectMapper.countProjectList");
	}

	public List<Project> getProjectList(ProjectPageNation pagination) {

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		return sqlSession.selectList("projectMapper.getProjectList",null,rowBounds);

	}

	public List<Project> selectProjectDetailValue(int projectNo) {
		
		System.out.println(projectNo + "====================== DAO의 projectNo은 잘 넘어왔능가");
		
		return sqlSession.selectList("projectMapper.selectProjectDetailValue",projectNo);
	}

}
