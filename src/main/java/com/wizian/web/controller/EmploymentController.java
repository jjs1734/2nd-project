package com.wizian.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class EmploymentController {
	
	@GetMapping("/index")
	public String index(org.springframework.ui.Model model) {
		
		model.addAttribute("test", "테스트입니다.");
		return "index";
		
	}
}