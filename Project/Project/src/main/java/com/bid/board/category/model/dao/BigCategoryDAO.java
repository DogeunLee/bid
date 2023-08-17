package com.bid.board.category.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.category.model.vo.BigCategory;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.member.model.vo.Member;

@Repository
public class BigCategoryDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	public List<BigCategory> getSearchBigCat() {
		return sqlSession.selectList("bigCatMapper.getSearchBigCat");
	}

	
	public List<DetailCategory> getSubCategories(int codeNo) {
		return sqlSession.selectList("detailCatMapper.getSubCategories", codeNo);
	}

	public List<DetailCategory> getSubCategorie(int i) {
		return sqlSession.selectList("detailCatMapper.getSubCategories", i);
	}
	
}	