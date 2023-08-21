package com.bid.board.main.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bid.board.common.Util;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Exp;
import com.bid.board.member.model.vo.Graduate;
import com.bid.board.member.model.vo.Member;
import com.google.gson.JsonObject;

@Controller
@SessionAttributes({"loginMember"})
public class MainController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BigCategoryService services;

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
		searchResultBack = service.searchResultBack(codeNo, codeId, memberName,startDate,endDate,memberSt,memberGender,memberLv);
		System.out.println(searchResultBack);
		
		model.addAttribute("bigCategoryList", bigCategoryList);
		model.addAttribute("searchResultBack",searchResultBack);
		 return new ResponseEntity<>(searchResultBack, HttpStatus.OK);
	}
	
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

	@ResponseBody
	@GetMapping("/memberIdDubCheck")
	public int memberIdDupCheck(String memberId) {

		int result = service.memberIdDupCheck(memberId);

		return result;
	}

	@ResponseBody
	@GetMapping("/telDupCheck")
	public int telDupCheck(String memberTel) {

		System.out.println(memberTel);

		int result = service.telDupCheck(memberTel);

		return result;
	}

	@ResponseBody
	@GetMapping("/emailDupCheck")
	public int emailDupCheck(String memberEmail) {

		System.out.println(memberEmail);
		
		 int result = service.emailDupCheck(memberEmail);
		 System.out.println("멤버이메일의 결과는 ================== " + result);
		 
		return result;
	}
	
	
	@PostMapping("/signUp")
	public String signUp(Member inputMember
						,Graduate graduate
						,Exp exp
						,Model model
						,String[] memberAddr
						,RedirectAttributes ra	) {
		
		inputMember.setMemberAddr( String.join(",,", memberAddr ));
		
		if (inputMember.getMemberAddr().equals(",,,,")) {
			
			inputMember.setMemberAddr(null);
		}
	
		String memberGender = inputMember.getMemberBirth();
		
		   int hyphenPosition = memberGender.indexOf('-');

	        if (hyphenPosition != -1 && hyphenPosition + 1 < memberGender.length()) {
	            char numberAfterHyphen = memberGender.charAt(hyphenPosition + 1);
	            System.out.println("하이폰 다음의 숫자: " + numberAfterHyphen); // 출력: 하이폰 다음의 숫자: 1
	            
	            if ( numberAfterHyphen == '1' || numberAfterHyphen == '3') {
	            	inputMember.setMemberGender("GEN01");
	            }else {
	            	inputMember.setMemberGender("GEN02");
	            }
	            
	        } else {
	            System.out.println("하이폰 뒤에 숫자가 없습니다.");
	        }
	        
	        

	
		System.out.println(inputMember);
		
		int result = service.signUp(inputMember, graduate, exp);

			String message =null;
	String path =null;

	
	if(result > 0) {
		message = "회원가입 성공!";
		path = "redirect:/signUp";
	}else {
		message = "회원가입 실패";
		path = "redirect:/signUp";
		
	}
		
	ra.addFlashAttribute("message", message);
		return path;
	}
	

	
	//데이터파싱 주민번호
	public static String getFormattedBirthFromSSN(String ssn) {
	    String year = ssn.substring(0, 2);
	    String month = ssn.substring(2, 4);
	    String day = ssn.substring(4, 6);

	    char identifier = ssn.charAt(7);
	    if (identifier == '1' || identifier == '2') {
	        year = "19" + year;
	    } else if (identifier == '3' || identifier == '4') {
	        year = "20" + year;
	    }
	    
	    return year +"-"+ month+"-"+ day;
	}
	
	public static String hideSSNBack(String ssn) {
	    if(ssn == null || ssn.length() != 14) {
	        return ssn; 
	    }
	    return ssn.substring(0, 8) + "*******";
	}
	
	@RequestMapping("/myPage/memberDetail/{memberNo}")
	public String memberDetail(
			@PathVariable("memberNo") int memberNo,
			Model model
			) {

	    Member member = service.getMemberInfo(memberNo);
	    
	    String hiddenSSN = hideSSNBack(member.getMemberBirth());
	    String formattedBirth = getFormattedBirthFromSSN(member.getMemberBirth());
	    
	    System.out.println(member.getMemberBirth());
	    
	    System.out.println(formattedBirth);

	    List<DetailCategory> lvOptions = services.getSubCategorie(20);
	    List<DetailCategory> gradOptions = services.getSubCategorie(50);
	    List<DetailCategory> genderOptions = services.getSubCategorie(11);

	    model.addAttribute("lvOptions", lvOptions);
	    model.addAttribute("gradOptions", gradOptions);
	    model.addAttribute("genderOptions", genderOptions);
	    model.addAttribute("hiddenSSN", hiddenSSN); 

	    model.addAttribute("formattedBirth", formattedBirth); 
	    model.addAttribute("memberInfo", member);
	    
		
		return  "myPage/memberDetail";
		
	}
	
	
	
	
	
	// 파일업로드 ******
	@PostMapping("/uploadImage/{memberNo}")
	@ResponseBody
	public String uploadImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();

		String webPath = "/resources/images/employee/";

		String fileRoot = request.getServletContext().getRealPath(webPath);

		String originalFileName = multipartFile.getOriginalFilename();
		String savedFileName = Util.fileRename(originalFileName);

		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); 
			jsonObject.addProperty("url", request.getContextPath() + webPath + savedFileName);
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	

	
}
