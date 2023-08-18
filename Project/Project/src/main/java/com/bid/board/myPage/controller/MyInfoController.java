package com.bid.board.myPage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Member;

@Controller
@RequestMapping("/myPage")
@SessionAttributes({"loginMember"})
public class MyInfoController {
	@Autowired
	private MemberService service;
	
	
	@RequestMapping("/memberDetailPw/{memberNo}")
	public String memberDetailPw(
			@PathVariable("memberNo") int memberNo,
			Model model
			) {
		
	    Member member = service.getMemberInfo(memberNo);

	    model.addAttribute("memberInfo", member);
		
		return "/myPage/memberDetailPw";
	}

	@ResponseBody
	@GetMapping("/memberDetail/emailDupCheck")
	public int myPageemailDupCheck(String memberEmail) {

		System.out.println(memberEmail);

		int result = service.emailDupCheck(memberEmail);
		System.out.println("멤버이메일의 결과는 ================== " + result);

		return result;
	}

	@ResponseBody
	@GetMapping("/memberDetail/telDupCheck")
	public int telDupCheck(String memberTel) {

		System.out.println(memberTel);

		int result = service.telDupCheck(memberTel);

		return result;
	}
	
	
	
	

	@PostMapping("/memberDetail/updateInfos/{memberNo}")
	public String updateInfos(
			Member inputMember
			,String[] memberAddr
			,RedirectAttributes ra

			,@PathVariable("memberNo") int memberNo
			) {


		inputMember.setMemberAddr( String.join(",,", memberAddr ));

		if (inputMember.getMemberAddr().equals(",,,,")) {

			inputMember.setMemberAddr(null);
		}


		int result = service.updateInfos(inputMember);

		System.out.println("업데이트결과 : 0 == 실패 &&&&&&&&& 1 == 성공!");

		String path = null;
		String message = null;

		if(result > 0 ) {
			path = "redirect:/myPage/memberDetail/" + memberNo;
			message = "정보수정에 성공하였습니다.";
		} else {
			message = "정보수정에 실패하였습니다.";
		}

		ra.addFlashAttribute("message",message);


		return path;
	} 
	


	
}
