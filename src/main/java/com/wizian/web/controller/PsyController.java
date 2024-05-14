package com.wizian.web.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PsyController {
	
	@GetMapping("/psycoun_proc")
	public String index() {

		return "psycoun_proc";
		
	}
	
	@GetMapping("/psycoun_type")
	public String showPsycounTypePage() {
		return "psycoun_type";
	}
	
	@GetMapping("/psycoun_app")
	public String showPsycounAppPage() {
		return "psycoun_app";
	}
	
}