package com.wizian.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PsyController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/psycoun_proc")
	public String index() {

		return "psycoun_proc";
		
	}
	
	@GetMapping("/psycoun_type")
	public String showPsycounTypePage() {
		return "psycoun_type";
	}
	
	@GetMapping("/psycoun_app")
	public String showPsycounAppPage(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		UserDTO userInfo = userService.userInfo(userId);
		model.addAttribute("userInfo", userInfo); // 사용자 정보
		return "psycoun_app";
	}
	
}