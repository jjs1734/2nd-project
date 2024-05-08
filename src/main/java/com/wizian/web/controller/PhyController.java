package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhyController {
	
	@GetMapping("/PhyCoun")
	public String index() {

		return "PhyCoun";
		
	}
}