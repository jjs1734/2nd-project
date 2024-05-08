package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmploymentController {
	
	@GetMapping("/jinsutest")
	public String index() {

		return "jinsutest";
		
	}
}