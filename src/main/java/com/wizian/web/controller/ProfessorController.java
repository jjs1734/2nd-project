package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

	@GetMapping("/pfCoun")
	public String pfCounRequest(){
		return "pfCoun";
	}
}
