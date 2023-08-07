package com.bid.board;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MemberService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		int memberNo = service.getmemberNo();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+memberNo);
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

}
