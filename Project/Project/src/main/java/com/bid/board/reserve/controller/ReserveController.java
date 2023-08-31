package com.bid.board.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bid.board.reserve.model.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	private ReserveService service;
	
	
	@RequestMapping("/insertNewMember")
	public int insertNewMember(
			@RequestParam(value = "projectNo", required = false) int projectNo,
			@RequestParam(value = "memberId", required = false ) List<String> memberId
			) {
		
		System.out.println(projectNo);
		System.out.println(memberId);
		
	
		 int result = service.insertNewMember(projectNo,memberId);
		 
		
		return 0;
		
	}
	
}
