package com.wizian.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizian.web.service.GroupService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/main")
	public String main(HttpSession session, Model model){
		
		String userId = (String) session.getAttribute("userId");
		
		if(userId != null) {
			model.addAttribute("userId", userId);
		} else {
			System.out.println("세션이 존재하지 않음");
		}
		
		
		List<String> mainGroupImg = groupService.mainGroupImg(); 
		model.addAttribute("mainGroupImg", mainGroupImg);
		
		return "main";
	}

}
