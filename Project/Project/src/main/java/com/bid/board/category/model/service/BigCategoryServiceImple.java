package com.bid.board.category.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.board.category.model.dao.BigCategoryDAO;
import com.bid.board.category.model.vo.BigCategory;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.member.model.vo.Member;


@Service
public class BigCategoryServiceImple implements BigCategoryService {

	@Autowired
	private BigCategoryDAO dao;

	@Override
	public Map<String, Object> searchBigCat() {

		List<BigCategory> searchBigCat = dao.getSearchBigCat();

		for (BigCategory bigCategory : searchBigCat) {
			switch (bigCategory.getCodeId()) {
			case "GEN":
				bigCategory.setCodeId("성별");
				break;
			case "STAT":
				bigCategory.setCodeId("상태");
				break;
			case "LV":
				bigCategory.setCodeId("레벨");
				break;
			}
		}

		Map<String, Object> getsearchBigCat = new HashMap<String, Object>();
		getsearchBigCat.put("searchBigCat", searchBigCat);

		return getsearchBigCat;
		
	}

	@Override
	public Map<String, Object> getSubCategories(int codeNo) {
		
		List<DetailCategory> subCategories = dao.getSubCategories(codeNo);

		
		Map<String, Object> getSubCategories = new HashMap<String, Object>();
		getSubCategories.put("subCategories", subCategories);

		return getSubCategories;
	}



}


