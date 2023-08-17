package com.bid.board.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.board.member.model.service.MemberService;

@Controller
@RequestMapping("/myPage/memberDetail")
public class MyInfoController {
	@Autowired
	private MemberService service;
	
	@ResponseBody
	@GetMapping("/emailDupCheck")
	public int myPageemailDupCheck(String memberEmail) {

		System.out.println(memberEmail);
		
		 int result = service.emailDupCheck(memberEmail);
		 System.out.println("멤버이메일의 결과는 ================== " + result);
		 
		return result;
	}
	
	@ResponseBody
	@GetMapping("/telDupCheck")
	public int telDupCheck(String memberTel) {

		System.out.println(memberTel);

		int result = service.telDupCheck(memberTel);

		return result;
	}
	
}
