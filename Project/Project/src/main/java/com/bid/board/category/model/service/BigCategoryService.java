package com.bid.board.category.model.service;

import java.util.List;
import java.util.Map;

import com.bid.board.category.model.vo.DetailCategory;

public interface BigCategoryService {

	Map<String, Object> searchBigCat();


	Map<String, Object> getSubCategories(int codeNo);

	List<DetailCategory> getSubCategorie(int i);

}
