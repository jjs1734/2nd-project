package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

	@GetMapping("/pfCounRequest")
	public String pfCounRequest(){
		return "pfCounRequest";
	}
}
