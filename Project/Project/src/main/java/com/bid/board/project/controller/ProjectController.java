package com.bid.board.project.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.category.model.service.BigCategoryService;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.corperation.model.vo.Corperation;
import com.bid.board.member.model.service.MemberService;
import com.bid.board.member.model.vo.Certi;
import com.bid.board.member.model.vo.Member;
import com.bid.board.project.model.service.ProjectService;
import com.bid.board.project.model.vo.Project;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService service;
	
	@RequestMapping("/newProj")
	public String insertNewProject(
			
			Model model,
			RedirectAttributes ra,
			Project project,
			String[] projectAddr
			
			) {
		
		String path = null;
		String message = null;

	

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

			System.out.println(project);
			
			service.insertNewProject(project);

			path = "redirect:/projectList";
			message = "등록하였습니다 :)";
		}

		ra.addFlashAttribute("message", message);

		return path;
	
	}

	
	@GetMapping("/selectDetailProject")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> selectProjectDeailValue(
			Model model,
			@RequestParam(value = "projectNo", required = false) int projectNo
			){
		
		System.out.println(projectNo);
		

		Map<String, Object> selectProjectDeailValue = null;
		selectProjectDeailValue = service.selectProjectDeailValue(projectNo);

		
		System.out.println(selectProjectDeailValue);
		
		
		return new ResponseEntity<>(selectProjectDeailValue, HttpStatus.OK);

	}


	
}
