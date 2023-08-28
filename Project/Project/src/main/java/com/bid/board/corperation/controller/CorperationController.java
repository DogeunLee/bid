package com.bid.board.corperation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bid.board.category.model.service.BigCategoryService;
import com.bid.board.category.model.vo.DetailCategory;
import com.bid.board.corperation.model.service.CorperationService;
import com.bid.board.corperation.model.vo.Corperation;

@Controller
public class CorperationController {

	@Autowired
	private BigCategoryService services;

	@Autowired
	private CorperationService service;

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
	
	
	@RequestMapping("/newCorp/corpDetail/updateInfo/{corpNo}")
	private String updateInfo(
			
			Model model,
			Corperation corp,
			String[] corpAddr,
			RedirectAttributes ra,
			HttpServletResponse resp,
			HttpServletRequest req
			) {
		
		
		corp.setCorpAddr( String.join(",,", corpAddr ));

		if (corp.getCorpAddr().equals(",,,,")) {

			corp.setCorpAddr(null);
		}
		
		System.out.println(corp);
		
		
		int result = 0;
		
		result = service.updateInfo(corp);
		
		String path = null;
		String message = null;
		
		if ( result > 0 ) {
			message = "update Success";
			path = "redirect:/newCorp/corpDetail/"+corp.getCorpNo();
			
		} else {
			message = "fail to Update";
			path = "redirect:/newCorp/corpDetail/"+corp.getCorpNo();
					
		}
		
		ra.addFlashAttribute("message", message);

		
		return path;
		
	}


}
