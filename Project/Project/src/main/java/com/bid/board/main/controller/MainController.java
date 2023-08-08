package com.bid.board.main.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.common.Util;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Member;
import com.google.gson.JsonObject;

@Controller
@SessionAttributes({"loginMember"})
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

		model.addAttribute("getMemberList", getMemberList);


		return "common/main";
	}



	@RequestMapping("/signUp")
	public String signUp(
			Model model
			) {

		return "common/signUp";
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
						,String[] memberAddr,
						@RequestParam(value = "imageUrl1", required = false) String imageUrl
						,RedirectAttributes ra	) {
		
		inputMember.setMemberAddr( String.join(",,", memberAddr ));
		
		if (inputMember.getMemberAddr().equals(",,,,")) {
			
			inputMember.setMemberAddr(null);
		}

		inputMember.setMemberImg(imageUrl);
	
		String memberGender = inputMember.getMemberBirth();
		
		   int hyphenPosition = memberGender.indexOf('-');

	        if (hyphenPosition != -1 && hyphenPosition + 1 < memberGender.length()) {
	            char numberAfterHyphen = memberGender.charAt(hyphenPosition + 1);
	            System.out.println("하이폰 다음의 숫자: " + numberAfterHyphen); // 출력: 하이폰 다음의 숫자: 1
	            
	            if ( numberAfterHyphen == '1' || numberAfterHyphen == '3') {
	            	inputMember.setMemberGender("M");
	            }else {
	            	inputMember.setMemberGender("F");
	            }
	            
	        } else {
	            System.out.println("하이폰 뒤에 숫자가 없습니다.");
	        }
	        
	        

	
		System.out.println(inputMember);
		
		int result = service.signUp(inputMember);

			String message =null;
	String path =null;

	
	if(result > 0) {
		message = "회원가입 성공!";
		path = "redirect:/";
	}else {
		message = "회원가입 실패";
		path = "redirect:/signUp";
		
	}
		
	ra.addFlashAttribute("message", message);
		return path;
	}
	
	
	// 파일업로드 ******
	@PostMapping("/uploadImage")
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
