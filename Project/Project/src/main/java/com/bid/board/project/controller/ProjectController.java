package com.bid.board.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.category.model.service.BigCategoryService;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Member;
import com.bid.board.project.model.service.ProjectService;
import com.bid.board.project.model.vo.Corperation;
import com.bid.board.project.model.vo.Project;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService service;

	@Autowired
	private BigCategoryService services;
	
	@PostMapping("/newCorp")
	public String insertNewCorp(
			Model model,
			RedirectAttributes ra,
			HttpServletResponse resp,
			HttpServletRequest req,
			Corperation corp,
			String[] corpAddr
			) {


		String path = null;
		String message = null;

		System.out.println(corp);

		if ( corp.getCorpName() == null) {
			path = "redirect:/newCorp";
			message = "등록에 실패하였습니다 :)";
		}
		else
		{

			corp.setCorpAddr( String.join(",,", corpAddr ));

			if (corp.getCorpAddr().equals(",,,,")) {

				corp.setCorpAddr(null);
			}

			service.inputNewCorp(corp);

			path = "redirect:/corpList";
			message = "등록하였습니다 :)";
		}

		ra.addFlashAttribute("message", message);

		return path;

	};

	@RequestMapping("/newCorp/corpDetail/{corpNo}")
	public String corpDetail(
			Model model,
			@PathVariable("corpNo") int corpNo,
			@RequestParam(value = "cp", required = false, defaultValue="1" ) int cp
			) {

		Corperation corp = service.getCorpInfo(corpNo);
		List<DetailCategory> upjOption = services.getSubCategorie(80);
		List<DetailCategory> uptOption = services.getSubCategorie(90);

		model.addAttribute("upjOption",upjOption);
		model.addAttribute("uptOption",uptOption);
		model.addAttribute("corp", corp);
		
		System.out.println(upjOption);
		System.out.println(uptOption);
		System.out.println(corp);


		return  "newCorp/corpDetail";
	}

	
	@RequestMapping("/newProj")
	public String insertNewProject(
			
			Model model,
			RedirectAttributes ra,
			Project project,
			String[] projectAddr
			
			) {
		
		String path = null;
		String message = null;

		System.out.println(project);

		if ( project.getProjectValue() == null) {
			path = "redirect:/project";
			message = "등록에 실패하였습니다 :)";
		}
		else
		{

			project.setProjectAddr( String.join(",,", projectAddr ));

			if (project.getProjectAddr().equals(",,,,")) {

				project.setProjectAddr(null);
			}

			service.insertNewProject(project);

			path = "redirect:/projectList";
			message = "등록하였습니다 :)";
		}

		ra.addFlashAttribute("message", message);

		return path;
	
	}
	
}
