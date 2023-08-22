package com.bid.board.main.controller;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.category.model.service.BigCategoryService;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
public class MainController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BigCategoryService services;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute Member inputMember,
			Model model,
			RedirectAttributes ra,
			HttpServletResponse resp,
			HttpServletRequest req,
			HttpSession session,
			SessionStatus status
			) {


		System.out.println(inputMember);


		Member loginMember = service.login(inputMember);


		String path = null;

		if(loginMember != null) { // 로그인 성공 시
			ra.addFlashAttribute("message", "로그인성공");
		    session.setAttribute("loginMember", loginMember);
		    Member checkMember = (Member)session.getAttribute("loginMember");
		    System.out.println(checkMember + "=========================================="); 
			path = "redirect:/main";

		} else {
			ra.addFlashAttribute("message", "실패");
			path = "redirect:/";
		}

		return path;

	}
	
	@RequestMapping("/main")
	public String main(
			Model model,
			@RequestParam(value = "bcategory", required = false) String bcategory,
			@RequestParam(value = "cp", required = false, defaultValue="1" ) int cp   
			) {
		
		Map<String, Object> getMemberList = null;
		getMemberList = service.getMemberList(cp);
		

		Map<String, Object> bigCategoryList = null;
		bigCategoryList = services.searchBigCat();
		
		model.addAttribute("getMemberList", getMemberList);
		model.addAttribute("bigCategoryList", bigCategoryList);

		
		System.out.println(getMemberList);
		
		return "common/main";
	}
	
	@RequestMapping("/signUp")
	public String signUp(
			Model model
			) {
		 List<DetailCategory> lvOptions = services.getSubCategorie(20);
		    List<DetailCategory> gradOptions = services.getSubCategorie(50);

		    model.addAttribute("lvOptions", lvOptions);
		    model.addAttribute("gradOptions", gradOptions);
		return "signUp/signUp";
	}
	
	@GetMapping("/searchMembers")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> searchResultBack(
			Model model,
			@RequestParam(value = "bcategory", required = false) int bcategory,
			@RequestParam(value = "subCategory", required = false) String codeId,	 
			@RequestParam(value = "memberName", required = false) String memberName,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "searchValue1", required = false) String memberSt,
			@RequestParam(value = "searchValue2", required = false) String memberGender,
			@RequestParam(value = "searchValue3", required = false) String memberLv,
			@RequestParam(value = "all", required = false) String all,
			@RequestParam(value = "cp", required = false, defaultValue="1" ) int cp 
			){
		
		Map<String, Object> bigCategoryList = null;
		bigCategoryList = services.searchBigCat();
		
		Integer codeNo = bcategory;

		Map<String, Object> searchResultBack = null;
		searchResultBack = service.searchResultBack(codeNo, codeId, memberName,startDate,endDate,memberSt,memberGender,memberLv, cp);
		System.out.println(searchResultBack);
		
		model.addAttribute("bigCategoryList", bigCategoryList);
		model.addAttribute("searchResultBack",searchResultBack);
		 return new ResponseEntity<>(searchResultBack, HttpStatus.OK);
	}
	
	
	
}
