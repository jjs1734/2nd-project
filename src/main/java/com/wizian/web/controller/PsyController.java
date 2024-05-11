package com.wizian.web.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PsyController {
	
	@GetMapping("/PsyCoun")
	public String index() {

		return "PsyCoun";
		
	}
}