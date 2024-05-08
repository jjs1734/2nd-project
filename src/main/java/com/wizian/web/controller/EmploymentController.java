package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmploymentController {
	
	@GetMapping("/employment")
	public String index() {

		return "employment";
		
	}
}