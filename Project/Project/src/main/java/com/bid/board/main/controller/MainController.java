package com.bid.board.main.controller;
import java.util.HashMap;
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
import com.bid.board.corperation.model.service.CorperationService;
import com.bid.board.corperation.model.vo.Corperation;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Member;
import com.bid.board.project.model.service.ProjectService;

@Controller
@SessionAttributes({"loginMember"})
public class MainController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private CorperationService cservice;
	
	@Autowired
	private ProjectService pservice;

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

	@RequestMapping("/project")
	public String project(
		Model model
			) {
		
		List<Corperation> corperation = pservice.getCorpName();
		
		model.addAttribute("corperation",corperation);
		
		return "project/project";
	}
	
	@GetMapping("/projectList")
	public String projectList(
			Model model,
			Corperation corp,
			@RequestParam(value="cp", required = false, defaultValue="1") int cp,
			@RequestParam(value = "bcategory", required = false) String bcategory
			) {
		
		Map<String, Object> getProjectList = null;
		getProjectList = pservice.getProjectList(cp);
		
		System.out.println(getProjectList);
		
// 여기
		Map<String, Object> getMemberList = null;
		getMemberList = service.getMemberList(cp);


		Map<String, Object> bigCategoryList = null;
		bigCategoryList = services.searchBigCat();

		model.addAttribute("getMemberList", getMemberList);
		model.addAttribute("bigCategoryList", bigCategoryList);
		
// 여기
		model.addAttribute("getProjectList",getProjectList);

		return "project/projectList";
	}
	
	@ResponseBody
	@RequestMapping("/getMoreProjectList")
	public  ResponseEntity<Map<String, Object>> getMoreProjectList(
			@RequestParam(value="cp", required = false, defaultValue="1") int cp
			){
		
		Map<String, Object> getProjectList = pservice.getProjectList(cp);
		return ResponseEntity.ok(getProjectList);
	}
	

	
	@ResponseBody
	@GetMapping("/getCorpManagerInfo")
	public ResponseEntity<Map<String, String>> getCorpManagerInfo(
	        @RequestParam(value = "corpNo", required = false) int corpNo
	) {
	    System.out.println(corpNo);
	    
	    List<Corperation> corpInfoList = pservice.getOtherInfo(corpNo);
	    
	    if (corpInfoList == null || corpInfoList.isEmpty()) {
	        return ResponseEntity.notFound().build(); 
	    }
	    
	    Corperation corpInfo = corpInfoList.get(0); 
	    
	    Map<String, String> responseMap = new HashMap<>();
	    responseMap.put("corpManager", corpInfo.getCorpManager());
	    responseMap.put("corpManagerTel", corpInfo.getCorpManagerTel());
	    
	    System.out.println(responseMap);
	    
	    return ResponseEntity.ok(responseMap);
	}
	
	@RequestMapping("/corpList")
	public String corpList(
			Model model,
			@RequestParam(value = "cp", required = false, defaultValue="1" ) int cp
			) {
		
		Map<String, Object> getCorpList = null;

		getCorpList = cservice.getCorpList(cp);
		model.addAttribute("getCorpList", getCorpList);
		
		System.out.println(getCorpList);
		return "newCorp/corpList";
	}

	@RequestMapping("/newCorp")
	public String newCorp(
			Model model
			) {
		List<DetailCategory> upjOption = services.getSubCategorie(80);
		List<DetailCategory> uptOption = services.getSubCategorie(90);

		model.addAttribute("upjOption",upjOption);
		model.addAttribute("uptOption",uptOption);


		return "newCorp/newCorp";
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
