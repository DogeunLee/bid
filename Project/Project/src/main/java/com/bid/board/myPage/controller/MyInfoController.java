package com.bid.board.myPage.controller;

import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.category.model.service.BigCategoryService;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Exp;
import com.bid.board.member.model.vo.Graduate;
import com.bid.board.member.model.vo.Member;

@Controller
@RequestMapping("/myPage")
@SessionAttributes({"loginMember"})
public class MyInfoController {

	@Autowired
	private MemberService service;

	@Autowired
	private BigCategoryService services;

	@RequestMapping("/memberDetailPw/{memberNo}")
	public String memberDetailPw(
			@PathVariable("memberNo") int memberNo,
			Certi certi,
			Model model
			) {

		Member member = service.getMemberInfo(memberNo);
		List<DetailCategory> statusOptions = services.getSubCategorie(10);
		List<Certi> certis = service.selectCerti(memberNo);

		model.addAttribute("certi", certis);
		model.addAttribute("statusOptions", statusOptions);
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
			,RedirectAttributes ra,
			Graduate graduate,
			Exp exp,
			Certi certiTemplate,
			@PathVariable("memberNo") int memberNo
			) {
	

		System.out.println(exp);
		System.out.println(graduate);
		System.out.println(certiTemplate);

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

	@PostMapping("/memberDetailPw/updateInfosPw/{memberNo}")
	public String updateInfosPw(
			@PathVariable("memberNo") int memberNo, // URL 경로의 {memberNo} 값을 가져오기 위해 사용
			@RequestParam("memberPw") String currentPassword, // 현재 비밀번호
			@RequestParam("newMemberPw") String newPassword, // 새로운 비밀번호
			RedirectAttributes ra
			) {

		System.out.println(memberNo + "현재 멤버는?");

		System.out.println(currentPassword+ "현재 멤버는?");
		System.out.println(newPassword+ "현재 멤버는?");

		int result = service.changePw(memberNo, currentPassword, newPassword);
		String message = null;
		String path = null;

		if(result == 0) {
			message = "비밀번호 변경에 실패하였습니다.";
			path = "redirect:/myPage/memberDetailPw/"+memberNo;
		} else {
			message = "비밀번호 변경 성공";
			path = "redirect:/myPage/memberDetailPw/"+memberNo;
		}
		ra.addFlashAttribute("message",message);

		return path;
	}

}
