package com.bid.board.project.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.project.model.vo.CorpPagination;
import com.bid.board.project.model.vo.Corperation;

@Repository
public class ProjectDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int inputNewCorp(Corperation corp) {
		return sqlSession.insert("corpMapper.inputNewCorp",corp);
	}

	public int getCorpListCount() {
		return sqlSession.selectOne("corpMapper.getCorpListCount");
	}

	public List<Corperation> getCorpList(CorpPagination pagenation) {
		
		int offset = (pagenation.getCurrentPage() - 1) * pagenation.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagenation.getLimit());

		return  sqlSession.selectList("corpMapper.getCorpList",null,rowBounds);
	}

	public Corperation getCorpInfo(int corpNo) {
		return sqlSession.selectOne("corpMapper.getCorpInfo", corpNo);
	}

	public List<Corperation> getCorpName() {
		return sqlSession.selectList("corpMapper.getCorpName");
	}

	public List<Corperation> getOtherInfo(int corpNo) {
		System.out.println(corpNo+"============DAO");
		return sqlSession.selectList("corpMapper.getOtherInfo", corpNo);
	}
	
}
