package com.wizian.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/main")
	public String main(){
		return "main";
	}
	
	@GetMapping("/pfCounRequest")
	public String pfCounRequest(){
		return "pfCounRequest";
	}

	@GetMapping("/maintest")
	public String maintest(){
		return "maintest";
	}

}
