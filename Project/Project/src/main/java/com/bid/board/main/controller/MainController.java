package com.bid.board.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bid.board.member.model.service.MemberService;

@Controller
public class MainController {
	
	@Autowired
	private MemberService service;

	@RequestMapping("/main")
	public String main(
			Model model,
			@RequestParam(value = "cp", required = false, defaultValue="1" ) int cp   
			) {
		
		Map<String, Object> getMemberList = null;

		getMemberList = service.getMemberList(cp);
		
		System.out.println(cp);
		
		model.addAttribute("getMemberList", getMemberList);
		
		return "common/main";
	}
}
