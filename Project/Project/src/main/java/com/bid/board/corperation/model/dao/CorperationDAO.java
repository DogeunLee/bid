package com.bid.board.corperation.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bid.board.corperation.model.vo.CorpPagination;
import com.bid.board.corperation.model.vo.Corperation;

@Repository
public class CorperationDAO {
	
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

	public int updateInfo(Corperation corp) {
		return sqlSession.update("corpMapper.updateInfo",corp);
	}

}
