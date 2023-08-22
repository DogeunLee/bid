package com.bid.board.category.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.board.category.model.service.BigCategoryService;

@Controller
public class Category {
	
	@Autowired
	private BigCategoryService services;
	
	@RequestMapping("/getSubCategories")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getSubCategories(
			Model model,
			@RequestParam("codeNo") int codeNo) {
		
	
			Map<String, Object> subCategory = null;
			subCategory = 	services.getSubCategories(codeNo);		
			
			model.addAttribute("subCategory", subCategory);
			
			System.out.println(subCategory);
			
	    return new ResponseEntity<>(subCategory, HttpStatus.OK);
	}
	
	@RequestMapping("/getSubOptions")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> selectSearchCategory(
			Model model,
			@RequestParam("codeNo") int codeNo) {
		
	
			Map<String, Object> subCategory = null;
			subCategory = 	services.getSubCategories(codeNo);		
			
			model.addAttribute("subCategory", subCategory);
			
			System.out.println(subCategory);
			
	    return new ResponseEntity<>(subCategory, HttpStatus.OK);
	}

}
