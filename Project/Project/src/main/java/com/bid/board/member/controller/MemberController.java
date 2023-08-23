package com.bid.board.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import com.bid.board.common.Util;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Exp;
import com.bid.board.member.model.vo.Graduate;
import com.bid.board.member.model.vo.Member;
import com.google.gson.JsonObject;

@Controller
@SessionAttributes({"loginMember"})
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private BigCategoryService services;
	
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
			
			Certi certi,
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
	    List<DetailCategory> statusOptions = services.getSubCategorie(10);
	    List<Certi> certis = service.selectCerti(memberNo);

	    model.addAttribute("lvOptions", lvOptions);
	    model.addAttribute("gradOptions", gradOptions);
	   
	    model.addAttribute("statusOptions", statusOptions);
	    model.addAttribute("genderOptions", genderOptions);
	    model.addAttribute("hiddenSSN", hiddenSSN); 
	    model.addAttribute("formattedBirth", formattedBirth); 
	    model.addAttribute("memberInfo", member);
	    model.addAttribute("certi", certis);
	    
	    System.out.println(member);
		System.out.println(certis);
	    
	    
		return  "myPage/memberDetail";
		
	}
	
	@PostMapping("/signUp")
	public String signUp(Member inputMember
			,Graduate graduate
			,Exp exp
			,Certi certiTemplate
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
		
	    int memberNo = service.signUp(inputMember, graduate, exp);
	    if (memberNo <= 0) {
	        ra.addFlashAttribute("message", "회원가입 실패");
	        return "redirect:/signUp";
	    }
	    
	    String[] certiNames = certiTemplate.getCertiName().split(",");
	    String[] certiDates = certiTemplate.getCertiDate().split(",");
	    boolean allSuccess = true;

	    
	    for (int i = 0; i < certiNames.length; i++) {
	        Certi certi = new Certi();
	        certi.setMemberNo(memberNo); // 생성된 memberNo 설정
	        certi.setCertiName(certiNames[i].trim());
	        certi.setCertiDate(certiDates[i].trim());
	        int result = service.addCerti(certi);
	        if (result <= 0) {
	            allSuccess = false;
	            break;
	        }
	    }

	    String message = allSuccess ? "회원가입 성공!" : "회원가입 실패";
	    ra.addFlashAttribute("message", message);
	    return "redirect:/signUp";

	}
	
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
